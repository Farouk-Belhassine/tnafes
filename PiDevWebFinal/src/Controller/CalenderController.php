<?php

namespace App\Controller;
use App\Entity\Event;

use App\Repository\EventRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\CalendarType;
use App\Repository\CalendarRepository;
use Symfony\Component\HttpFoundation\Request;

class CalenderController extends AbstractController
{
    /**
     * @return Response
     * @Route("/calender", name="calender")
     */
    public function index(EventRepository $event): Response
    { $events = $event->findAll();

    $evenement= [];
    foreach ($events as $eventt)
        $evenement[]= [

            'lieu'=>$eventt->getlieu(),

        'nb_place'=> $eventt->getNbPlace(),
            'date'=> $eventt->getDateEvent()->format('Y-m-d'),





        ];
        //$events[]=$calendarService->loadData($evenement);
        //$data = new JsonResponse($evenement);
       // return $response->setData(array($events));
        $data=  json_encode($evenement);

        return $this->render('calender/index.html.twig', compact('data')
        );
    }
}
