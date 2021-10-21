<?php

namespace App\Controller;

use App\Entity\Consultation;
use App\Form\ConsultationType;
use App\Repository\UtilisateurRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;


/**
 * @Route("/consultation")
 */
class ConsultationController extends AbstractController
{
    public function stats()
    {
        $consultation = $this->getDoctrine()
            ->getRepository(Consultation::class);
        return $consultation->createQueryBuilder('u')
            ->Select('COUNT(u.etat) as pff')
            ->addSelect('u.etat')
            ->groupBy('u.etat')
            ->getQuery()
            ->getResult()
            ;
    }
    /**
     * @IsGranted("ROLE_COACH")
     * @Route("/stat", name="consultation_stat", methods={"GET"})
     */
    public function stat(ConsultationController $consultationController): Response
    {
        dump( $consultationController->stats());
        return $this->render('consultation/stat.html.twig', [
            'users' => $consultationController->stats(),
        ]);
    }
    /**
     * @IsGranted("ROLE_COACH")
     * @Route("/", name="consultation_index", methods={"GET"})
     */
    public function index(): Response
    {
        $consultations = $this->getDoctrine()
            ->getRepository(Consultation::class)
            ->findAll();

        return $this->render('consultation/index.html.twig', [
            'consultations' => $consultations,
        ]);
    }
    /**
     * @IsGranted("ROLE_CLIENT")
     * @Route("/indexclient", name="consultation_indexclient", methods={"GET"})
     */
    public function indexclient(): Response
    {
        $consultations = $this->getDoctrine()
            ->getRepository(Consultation::class)
            //->findby(['idClient'=>$user->getId()]);
        ->findAll();

        return $this->render('consultation/indexclient.html.twig', [
            'consultations' => $consultations,
        ]);
    }

    /**
     * @IsGranted("ROLE_CLIENT")
     * @Route("/new", name="consultation_new", methods={"GET","POST"})
     */
    public function new(Request $request , \Swift_Mailer $mailer ,UtilisateurRepository $UtilisateurRepository): Response
    {
        $user=$this->get('security.token_storage')->getToken()->getUser();
        $consultation = new Consultation();
        $form = $this->createForm(ConsultationType::class, $consultation);
        $form->handleRequest($request);
        $consultation->setEtat("att");
        if ($form->isSubmitted() && $form->isValid()) {
            $consultation->setIdClient($user->getId());
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($consultation);
            $entityManager->flush();

            //mailing
            $coach = $UtilisateurRepository->find($consultation->getId());
            $message = (new \Swift_Message('Nouvelle Consultation','Vous avez une nouvelle Consultation'))
                ->setFrom('tnafes.tnafes@gmail.com')
                ->setTo($coach->getEmail());



            $mailer->send($message);
            $this->addFlash('message', 'le message a bien été envoyé');



            return $this->redirectToRoute('consultation_indexclient');
        }

        return $this->render('consultation/new.html.twig', [
            'consultation' => $consultation,
            'form' => $form->createView(),
            'user' => $user,
        ]);
    }

    /**
     * @IsGranted("ROLE_COACH")
     * @Route("/{idConsultation}", name="consultation_show", methods={"GET"})
     */
    public function show(Consultation $consultation, UtilisateurRepository $UtilisateurRepository): Response
    {
        $client = $UtilisateurRepository->find($consultation->getIdClient());
        $coach = $UtilisateurRepository->find($consultation->getId());
        return $this->render('consultation/show.html.twig', [
            'consultation' => $consultation,
            'coach' => $coach,
            'client' => $client,
        ]);
    }
    /**
     * @IsGranted("ROLE_CLIENT")
     * @Route("/client/{idConsultation}", name="consultation_showclient", methods={"GET"})
     */
    public function showclient(Consultation $consultation, UtilisateurRepository $UtilisateurRepository): Response
    {
        $client = $UtilisateurRepository->find($consultation->getIdClient());
        $coach = $UtilisateurRepository->find($consultation->getId());
        return $this->render('consultation/showclient.html.twig', [
            'consultation' => $consultation,
            'coach' => $coach,
            'client' => $client,
        ]);
    }

    /**
     * @IsGranted("ROLE_CLIENT")
     * @Route("/{idConsultation}/edit", name="consultation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Consultation $consultation): Response
    {
        $form = $this->createForm(ConsultationType::class, $consultation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('consultation_indexclient');
        }

        return $this->render('consultation/edit.html.twig', [
            'consultation' => $consultation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @IsGranted("ROLE_COACH")
     * @Route("/{idConsultation}", name="consultation_delete", methods={"POST"})
     */
    public function delete(Request $request, Consultation $consultation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$consultation->getIdConsultation(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($consultation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('consultation_index');
    }
    /**
     * @IsGranted("ROLE_CLIENT")
     * @Route("/client/{idConsultation}", name="consultation_deleteclient", methods={"POST"})
     */
    public function deleteclient(Request $request, Consultation $consultation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$consultation->getIdConsultation(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($consultation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('consultation_indexclient');
    }


    /**
     * @IsGranted("ROLE_COACH")
     * @Route("/acpcons/{idConsultation}", name="acpcons", methods={"GET"})
     */
    public function acpcons(Request $request, Consultation $consultation, \Swift_Mailer $mailer, UtilisateurRepository $UtilisateurRepository): Response
    {
        $client = $UtilisateurRepository->find($consultation->getIdClient());
        $entityManager = $this->getDoctrine()->getManager();
        $consultation->setEtat("acc");
        $entityManager->flush();
        //mailing
        $message = (new \Swift_Message('Nouvelle Consultation','Vous avez une nouvelle Consultation'))
            ->setFrom('tnafes.tnafes@gmail.com')
            ->setTo($client->getEmail());

        $mailer->send($message);
        $this->addFlash('message', 'Votre Consultation a éte accepté');

        return $this->render('consultation/acpcons.html.twig');
    }

    /**
     * @IsGranted("ROLE_COACH")
     * @Route("/refcons/{idConsultation}", name="refcons", methods={"GET"})
     */
    public function refcons(Request $request, Consultation $consultation): Response
    {

        $entityManager = $this->getDoctrine()->getManager();
        $consultation->setEtat("ref");
        $entityManager->flush();

        return $this->render('consultation/refcons.html.twig');
    }


}
