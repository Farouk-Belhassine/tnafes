<?php

namespace App\Controller;

use App\Entity\Message;
use App\Form\MessageType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Validator\Constraints\DateTime;
use App\Repository\UtilisateurRepository;

/**
 * @Route("/message")
 */
class MessageController extends AbstractController
{
    /**
     * @Route("/index", name="message_index", methods={"GET"})
     */
    public function index(): Response
    {
        $messages = $this->getDoctrine()
            ->getRepository(Message::class)
            ->findAll();

        return $this->render('message/index.html.twig', [
            'messages' => $messages,
        ]);
    }
    /**
     * @Route("/indexclient", name="message_indexclient", methods={"GET"})
     */
    public function indexclient(): Response
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $iduser = $user->getId();
        $tt = $iduser;
        $messages = $this->getDoctrine()
            ->getRepository(Message::class)
            ->findAll();

        return $this->render('message/indexclient.html.twig', [
            'messages' => $messages,
            'iduser' => $tt,

        ]);
    }

    /**
     * @Route("/new", name="message_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $message = new Message();
        $form = $this->createForm(MessageType::class, $message);
        $form->handleRequest($request);
        $heure = new DateTime('now');
        $message->setHeure(new \DateTime());

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($message);
            $entityManager->flush();

            return $this->redirectToRoute('message_index');
        }

        return $this->render('message/new.html.twig', [
            'message' => $message,
            'form' => $form->createView(),
        ]);
    }


/**
 * @Route("/newclient", name="message_newclient", methods={"GET","POST"})
 */
public function newclient(Request $request): Response
    {
        $message = new Message();
        $form = $this->createForm(MessageType::class, $message);
        $form->handleRequest($request);
        $heure = new DateTime('now');
        $message->setHeure(new \DateTime());

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($message);
            $entityManager->flush();

            return $this->redirectToRoute('message_indexclient');
        }

        return $this->render('message/newclient.html.twig', [
            'message' => $message,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idMessage}", name="message_show", methods={"GET"})
     */
    public function show(Message $message, UtilisateurRepository $UtilisateurRepository): Response
    {
        $client = $UtilisateurRepository->find($message->getIdClient());
        $coach = $UtilisateurRepository->find($message->getIdCoach());
        return $this->render('message/show.html.twig', [
            'message' => $message,
            'client' => $client,
            'coach' => $coach,
        ]);
    }
    /**
     * @Route("/client/{idMessage}", name="message_showclient", methods={"GET"})
     */
    public function showclient(Message $message, UtilisateurRepository $UtilisateurRepository): Response
    {
        $client = $UtilisateurRepository->find($message->getIdClient());
        $coach = $UtilisateurRepository->find($message->getIdCoach());
        return $this->render('message/showclient.html.twig', [
            'message' => $message,
            'client' => $client,
            'coach' => $coach,

        ]);
    }

    /**
     * @Route("/{idMessage}/edit", name="message_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Message $message): Response
    {
        $form = $this->createForm(MessageType::class, $message);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('message_index');
        }

        return $this->render('message/edit.html.twig', [
            'message' => $message,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idMessage}", name="message_delete", methods={"POST"})
     */
    public function delete(Request $request, Message $message): Response
    {
        if ($this->isCsrfTokenValid('delete'.$message->getIdMessage(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($message);
            $entityManager->flush();
        }

        return $this->redirectToRoute('message_index');
    }
    /**
     * @Route("/client/{idMessage}", name="message_deleteclient", methods={"POST"})
     */
    public function deleteclient(Request $request, Message $message): Response
    {
        if ($this->isCsrfTokenValid('delete'.$message->getIdMessage(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($message);
            $entityManager->flush();
        }

        return $this->redirectToRoute('message_indexclient');
    }
}
