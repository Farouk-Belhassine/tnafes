<?php

namespace App\Controller;

use App\Entity\Event;
use App\Entity\Participation;
use App\Form\ParticipationType;
use App\Repository\EventRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Twilio\Rest\Client;

/**
 * @Route("/participation")
 */
class ParticipationController extends AbstractController
{
    /**
     * @Route("/", name="participation_index", methods={"GET"})
     */
    public function index(): Response
    {
        $participations = $this->getDoctrine()
            ->getRepository(Participation::class)
            ->findAll();

        return $this->render('participation/index.html.twig', [
            'participations' => $participations,
        ]);
    }

    /**
     * @Route("/{idEvent}", name="participation_new", methods={"GET","POST"})
     */
    public function new(Request $request, Event $event,FlashyNotifier $flashyNotifier): Response
    {   $user=$this->get('security.token_storage')->getToken()->getUser();
        $b=$event->getNbPlace();
        $participation = new Participation($event,$user);
        $form = $this->createForm(ParticipationType::class, $participation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            /*$ch = curl_init();
            $certificate_location = 'C:\wamp64\cacert.pem';
            curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, $certificate_location);
            curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, $certificate_location);*/
            $sid = "AC8824002e1fffacce99fab1c0d770a78a"; // Your Account SID from www.twilio.com/console
            $token = "8872a4599f2d1013698e62bb9e9e0f68"; // Your Auth Token from www.twilio.com/console
            $number='+216'.$user->getNumtel();
            dump($number);
            $client = new Client($sid, $token);
            $message = $client->messages->create(
                '+216'.$user->getNumtel(), // Text this number
                [
                    'from' => '+15106620427', // From a valid Twilio number
                    'body' => 'votre participation avec TNAFES est confirmé !'
                ]
            );

           $event->setNbPlace($event->getNbPlace()-1);
           $participation->setDateEvenement($event->getDateEvent());
            $participation->setDatePart(new \DateTime('now'));
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($participation);
            $entityManager->flush();

            $flashyNotifier->success('participation ajoutée','http://your-awesome-link.com');
            return $this->redirectToRoute('event_indexF');


        }


        return $this->render('participation/new.html.twig', [

            'participation' => $participation,
            'form' => $form->createView(),
            'b'=> $b,
            'user'=>$user,
        ]);



    }


    /**
     * @Route("/{idParticipation}", name="participation_show", methods={"GET"})
     */
    public function show(Participation $participation): Response
    {
        return $this->render('participation/show.html.twig', [
            'participation' => $participation,
        ]);
    }

    /**
     * @Route("/{idParticipation}/edit", name="participation_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Participation $participation): Response
    {
        $form = $this->createForm(ParticipationType::class, $participation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('participation_index');
        }

        return $this->render('participation/edit.html.twig', [
            'participation' => $participation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idParticipation}", name="participation_delete", methods={"POST"})
     */
    public function delete(Request $request, Participation $participation): Response
    {
        if ($this->isCsrfTokenValid('delete'.$participation->getIdParticipation(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($participation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('participation_index');
    }
}
