<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class TemplatebackController extends AbstractController
{
    /**
     * @Route("/templateback", name="templateback")
     */
    public function index(): Response
    {
        return $this->render('templateback/index.html.twig', [
            'controller_name' => 'TemplatebackController',
        ]);
    }
    /**
     * @Route("/event", name="event")
     */
    public function event(): Response
    {
        return $this->render('templateback/event.html.twig', [
            'controller_name' => 'TemplatebackController',
        ]);
    }

    /**
     * @Route("/categorieEvent", name="categorieEvent")
     */
    public function categorieEvent(): Response
    {
        return $this->render('templateback/categorie_event.html.twig', [
            'controller_name' => 'TemplatebackController',
        ]);
    }

}
