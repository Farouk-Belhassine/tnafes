<?php

namespace App\Controller;

use App\Entity\CategorieEvent;
use App\Entity\Event;
use App\Repository\EventRepository;
use App\Entity\Participation;
use App\Form\EventType;
//use CalendarBundle\Serializer\SerializerInterface;
use phpDocumentor\Reflection\DocBlock\Serializer;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;


/**
 * @Route("/event")
 */
class EventController extends AbstractController
{
    /**
     * @Route("/", name="event_index", methods={"GET"})
     */
    public function index(): Response
    {
        $events = $this->getDoctrine()
            ->getRepository(Event::class)
            ->findAll();

        return $this->render('event/index.html.twig', [
            'events' => $events,
        ]);
    }



    /**
     * @Route("/Front", name="event_indexF", methods={"GET"})
     */
    public function indexF(): Response
    {
        $events = $this->getDoctrine()
            ->getRepository(Event::class)
            ->findAll();

        return $this->render('event/indexF.html.twig', [
            'events' => $events,
        ]);
    }





        /**
     * @Route("/new", name="event_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $event = new Event();
        $form = $this->createForm(EventType::class, $event);
        $form->handleRequest($request);




        if ($form->isSubmitted() && $form->isValid()) {

            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($event);
            $entityManager->flush();

            return $this->redirectToRoute('event_index');
        }

        return $this->render('event/new.html.twig', [
            'event' => $event,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idEvent}", name="event_show", methods={"GET"})
     */
    public function show(Event $event): Response
    {
        return $this->render('event/show.html.twig', [

            'event' => $event,
        ]);
    }

    /**
     * @Route("/show/{idEvent}", name="event_show2", methods={"GET"})
     */
    public function show2(Event $event): Response
    {$a=$event->getNbPlace();
        $this->get('session')->getFlashBag()->add(
            'notice',
            'Date invalide ,Evenement expiré!'
        );
        return $this->render('event/showF.html.twig', [
            'a'=>$a,
            'event' => $event,
        ]);
    }
    /**
     * @Route("/{idEvent}/edit", name="event_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Event $event): Response
    {
        $form = $this->createForm(EventType::class, $event);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('event_index');
        }

        return $this->render('event/edit.html.twig', [
            'event' => $event,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idEvent}", name="event_delete", methods={"POST"})
     */
    public function delete(Request $request, Event $event): Response
    {
        if ($this->isCsrfTokenValid('delete'.$event->getIdEvent(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($event);
            $entityManager->flush();
        }

        return $this->redirectToRoute('event_index');
    }
    /**
     * @Route("/c/searchEvent ", name="searchEvent")
     */
    public function searchEvent(EventRepository $repository, Request $request,NormalizerInterface $Normalizer)
    {
$requestString=$request->get('searchValue');
    $Events = $repository->findeventparlieu($requestString);
$jsonContent = $Normalizer->normalize($Events, 'json',['Groups'=>'
events:read']);
        $jsonc=json_encode($jsonContent);
        if (  $jsonc == "[]" )
        {
            return new Response(null);
        }
        else{        return new Response($jsonc);


}}


    /**
     * @Route("/e/liste", name="liste")
     */

    public function getevents(NormalizerInterface $Normalizer):Response
    {   $rep=$this->getDoctrine()->getRepository(Event::class);
        $av=$rep->findAll();
        $json=$Normalizer->normalize($av,'json',["groups"=>'post:read']);
        return  new Response(json_encode($json));

    }




    /**
     * @Route("/e/{idEvent}", name="id")
     */
    public function eventid (EventRepository $repo,$idEvent, NormalizerInterface $Normalizer)
    {
        $events=$repo->find($idEvent);
        $json=$Normalizer->normalize($events,'json',['groups'=>'
post:read']);
        return new Response(json_encode($json));

    }
    /**
     * @Route("/add/new", name="add")
     */
    public function addEventJSON(Request  $request, NormalizerInterface  $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $event = new Event();
        $event->setLieu((string)$request->get('lieu'));
        $event->setNbPlace((int)$request->get('nbPlace'));
        $event->setDateEvent(new \DateTime($request->get('DateEvent')) );
     //  $event->setdescriptions((string)$request->get('descriptions'));
      // $event->setcategory($request->get('category'));
        //stanani nchouf mte3i 1 mino
       $event->setDescription($request->get('Description'));
        $em->persist($event);
        $em->flush();
        $jsonContent = $normalizer->normalize($event, 'json',['groups'=>'
post:read']);
        return new Response(json_encode($jsonContent));



    }


    /**
     * @Route("/update/{idEvent}", name="update")
     */
    public function updateEventJSON(Request  $request, NormalizerInterface  $normalizer,$idEvent)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository(Event::class)->find($idEvent);
        $event->setLieu((string)$request->get('lieu'));
        $event->setNbPlace((int)$request->get('nbPlace'));
        $event->setDateEvent(new \DateTime($request->get('DateEvent')) );
       // $event->setdescriptions($request->get('descriptions'));
        //$event->setcategory($request->get('category'));
      //  $event->setDescription($request->get('Description'));
        $em->flush();
        $jsonContent = $normalizer->normalize($event, 'json',['groups'=>'
post:read']);
        return new Response("updated successfully".json_encode($jsonContent));



    }

    /**
     * @Route("/remove/{idEvent}", name="remove")
     */
    public function removeEventJSON(Request  $request, NormalizerInterface  $normalizer,$idEvent)
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository(Event::class)->find($idEvent);

        $em->remove($event);
        $em->flush();
        $jsonContent = $normalizer->normalize($event, 'json',['groups'=>'
post:read']);
        return new Response("deleted successfully".json_encode($jsonContent));



    }
}
