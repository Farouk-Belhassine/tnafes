<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\ReclamationType;
use App\Form\ReclamationType2;
use App\Repository\ReclamationRepository;
use Knp\Component\Pager\PaginatorInterface;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use function MongoDB\BSON\toJSON;
use MercurySeries\FlashyBundle\FlashyNotifier;

/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{
    /**
     * @Route("/", name="reclamation_index", methods={"GET"})
     */
    public function index(Request $request,ReclamationRepository $repository,FlashyNotifier $flashy,PaginatorInterface $paginator): Response
    {
        $donnees=$repository->findAll();
        $reclamations=$paginator->paginate(
            $donnees,// Requête contenant les données à paginer (ici les publications)
            $request->query->getInt('page',1),// Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            7   // Nombre de résultats par page
        );
        $nb=0;


        foreach ($reclamations as $rec){
            $currentdate=new \DateTime('now');
            $creationdate=$rec->getDate();
            $diff=date_diff($currentdate,$creationdate);
            $days=intval($diff->format("%d"));
            if($days>3 && $rec->getDatetraitement()==null){
                $nb++;
                $donnees=$repository->triedecroissant();
                $reclamations=$paginator->paginate(
                    $donnees,// Requête contenant les données à paginer (ici les publications)
                    $request->query->getInt('page',1),// Numéro de la page en cours, passé dans l'URL, 1 si aucune page
                    7   // Nombre de résultats par page
                );

                if($nb==1)
                {
                    $flashy->warning($nb.' réclamation a depassée 72h depuis sa création,veuillez la traiter plus tôt possible!', 'http://your-awesome-link.com');
                }
                else {


                    $flashy->warning($nb . ' réclamation ont depassées 72h depuis leur création,veuillez les traiter plus tôt possible!', 'http://your-awesome-link.com');
                }
            }
        }


        return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamations,


        ]);
    }
    /**
     * @Route("/stats", name="stats")
     */
    public function index2(ReclamationRepository $repository)
    {
        $reclamations=$repository->countByDate();
        $dates=[];
        $reclamationCount=[];
        foreach($reclamations as $reclamation){
            $dates[] = $reclamation['dateReclam'];
            $reclamationCount[] = $reclamation['count'];
        }
        dump($reclamationCount);
        return $this->render('reclamation/stats.html.twig',[
            'dates' => json_encode($dates),
            'reclamationCount' => json_encode($reclamationCount),

        ]);
    }

    /**
     * @Route("/new", name="reclamation_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $user=$this->get('security.token_storage')->getToken()->getUser();
        $reclamation = new Reclamation($user);
        $form = $this->createForm(ReclamationType::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($reclamation);
            $entityManager->flush();

            return $this->redirectToRoute('publication_index2');
        }

        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form2' => $form->createView(),
            'user'=>$user,
        ]);
    }

    /**
     * @Route("/{idReclamation}", name="reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation): Response

    {
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
        ]);
    }

    /**
     * @Route("/{idReclamation}/edit", name="reclamation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Reclamation $reclamation,\Swift_Mailer $mailer): Response
    {
        $form = $this->createForm(ReclamationType2::class, $reclamation);
        $form->handleRequest($request);


        if ($form->isSubmitted() && $form->isValid()) {

            if ($reclamation->getEtat() == 'Traitée') {
                $reclamation->setDateTraitement(new \DateTime('now'));

            }

            $this->getDoctrine()->getManager()->flush();
            $nom = $reclamation->getNomUser();
            $prenom = $reclamation->getPrenomUser();
            $email = $reclamation->getEmail();
            $phone = $reclamation->getNumtel();
            $message = (new \Swift_Message('Retour sur réclamation'))
                ->setFrom('tnafes.tnafes@gmail.com')
                ->setTo($email)
                ->setBody(
                    "Bonjour,<h1>Mr/Mme $nom $prenom ,votre réclamation est traitée avec succés,merci pour votre confiance.</h1>",
                    'text/html'
                );


            if ($reclamation->getEtat() == 'Traitée') {
                $mailer->send($message);

            }
            return $this->redirectToRoute('reclamation_index');
        }


        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form2' => $form->createView(),

        ]);
    }

    /**
     * @Route("/s/searchrec ", name="searchrec")
     */
    public function searchrecbyname(Request $request,NormalizerInterface $Normalizer,ReclamationRepository $repository):Response
    {

        $requestString=$request->get('searchValue');
        $reclamations = $repository->findrecByusername($requestString);
        $jsonContent = $Normalizer->normalize($reclamations, 'json',['groups'=>'reclamation']);
        $jsonc=json_encode($jsonContent);
        dump($jsonc);
        if(  $jsonc == "[]" )
        {
            return new Response(null);
        }
        else{        return new Response($jsonc);
        }

    }

}
