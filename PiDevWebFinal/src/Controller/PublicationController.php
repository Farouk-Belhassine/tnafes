<?php

namespace App\Controller;

use App\Entity\Avis;
use App\Entity\Commentaire;
use App\Entity\Publication;
use App\Form\AvisType;
use App\Form\CommentaireType;
use App\Form\PublicationType;
use App\Repository\PublicationRepository;
use Knp\Component\Pager\PaginatorInterface;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Snipe\BanBuilder\CensorWords;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/publication")
 */
class PublicationController extends AbstractController
{
    /**
     * @Route("/", name="publication_index", methods={"GET"})
     */
    public function index(NormalizerInterface $Normalizer,PaginatorInterface $paginator,Request $request,PublicationRepository $repository): Response
    {
        $donnees =$repository->findAll();
        $publications=$paginator->paginate(
            $donnees,// Requête contenant les données à paginer (ici les publications)
            $request->query->getInt('page',1),// Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3   // Nombre de résultats par page
        );
        $jsoncontent =$Normalizer->normalize($publications,'json',['groups'=>'publications:read']);
        return $this->render('publication/index.html.twig', [
            'publications' => $publications,
            json_encode($jsoncontent),

        ]);
    }
    /**
     * @Route("/pub", name="publication_index2", methods={"GET"})
     */
    //front
    public function index2(NormalizerInterface $Normalizer,Request $request,PaginatorInterface $paginator,PublicationRepository $repository): Response
    {
        $donnees =$repository->findAll();
        $publications=$paginator->paginate(
            $donnees,// Requête contenant les données à paginer (ici les publications)
            $request->query->getInt('page',1),// Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3   // Nombre de résultats par page
        );
        $jsoncontent =$Normalizer->normalize($publications,'json',['groups'=>'publications:read']);
        return $this->render('publication/index2.html.twig', [
            'publications' => $publications,
            json_encode($jsoncontent),
        ]);
    }
    /**
     * @Route("/new", name="publication_new", methods={"GET","POST"})
     */
    public function new(Request $request,NormalizerInterface $Normalizer,FlashyNotifier $flashyNotifier): Response
    {
        $user=$this->get('security.token_storage')->getToken()->getUser();
        $publication = new Publication($user);
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            dump($publication);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($publication);
            $entityManager->flush();
            $flashyNotifier->success('Publication ajoutée avec succès','http://your-awesome-link.com');
            return $this->redirectToRoute('publication_index');
        }
        $jsoncontent =$Normalizer->normalize($publication,'json',['groups'=>'publications:read']);
        return $this->render('publication/new.html.twig', [
            'publication' => $publication,
            'form' => $form->createView(),
            json_encode($jsoncontent),
        ]);
    }

    /**
     * @Route("/{idPub}", name="publication_show", methods={"GET","POST"})
     */
    public function show(Publication $publication,Request $request,NormalizerInterface $Normalizer): Response
    {
        $commentaires = $this->getDoctrine()
            ->getRepository(Commentaire::class)
            ->findByIdpub($publication->getIdPub());

        $jsoncontent =$Normalizer->normalize($publication,'json',['groups'=>'publications:read']);
        return $this->render('publication/show.html.twig', [
            'publication' => $publication,
            'commentaires'=>$commentaires,
            json_encode($jsoncontent),
        ]);
    }
    /**
     * @Route("/pub/{idPub}", name="publication_front", methods={"GET","POST"})
     */
    //front
    public function show2(Publication $publication,Request $request ,PublicationRepository $repository,NormalizerInterface $Normalizer,FlashyNotifier $flashyNotifier): Response
    {
        $user=$this->get('security.token_storage')->getToken()->getUser();
        dump($user);
        $publications = $repository->trie3();
        $commentaires = $this->getDoctrine()
            ->getRepository(Commentaire::class)
            ->findByIdpub($publication->getIdPub());
        $commentaire = new Commentaire($publication,$user);
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);
        $nb=$publication->getNbcomment();

        if ($form->isSubmitted() && $form->isValid()) {
            $nb++;
            $publication->setNbcomment($nb);
            dump($publication->getNbcomment());
            $this->getDoctrine()->getManager()->flush();
            $censor = new CensorWords;
            $langs = array('fr','it','en-us','en-uk','de','es');
            $badwords = $censor->setDictionary($langs);
            $censor->setReplaceChar("*");
            $string = $censor->censorString($commentaire->getContenuComment());
            $commentaire->setContenuComment($string['clean']);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($commentaire);
            $entityManager->flush();
            return $this->redirectToRoute('publication_index2');

        }

        $jsoncontent =$Normalizer->normalize($publications,'json',['groups'=>'publications:read']);
        return $this->render('publication/publication.html.twig', [
            'publication' => $publication,
            'publications' => $publications,
            'commentaire' => $commentaire,
            'formC' => $form->createView(),
            'commentaires'=>$commentaires,
            'user'=>$user,
            json_encode($jsoncontent),
        ]);
    }
    /**
     * @Route("/{idPub}/edit", name="publication_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Publication $publication,NormalizerInterface $Normalizer): Response
    {
        $form = $this->createForm(PublicationType::class, $publication);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $publication->setDatePublication(new \DateTime('now'));
            $this->getDoctrine()->getManager()->flush();
            return $this->redirectToRoute('publication_index');
        }
        $jsoncontent =$Normalizer->normalize($publication,'json',['groups'=>'publications:read']);
        return $this->render('publication/edit.html.twig', [
            'publication' => $publication,
            'form' => $form->createView(),
            json_encode($jsoncontent),
        ]);
    }

    /**
     * @Route("/{idPub}", name="publication_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Publication $publication,NormalizerInterface $Normalizer,FlashyNotifier $flashyNotifier): Response
    {      $commentaires = $this->getDoctrine()
        ->getRepository(Commentaire::class)
        ->findByIdpub($publication->getIdPub());
        if ($this->isCsrfTokenValid('delete'.$publication->getIdPub(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($publication);
            $entityManager->flush();
        }
        $jsoncontent =$Normalizer->normalize($publication,'json',['groups'=>'publications:read']);
        dump( json_encode($jsoncontent));
        $flashyNotifier->success('Publication supprimée avec succès','http://your-awesome-link.com');
        return $this->redirectToRoute('publication_index');
    }


}
