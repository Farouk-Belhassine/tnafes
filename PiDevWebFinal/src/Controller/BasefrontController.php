<?php

namespace App\Controller;
use App\Entity\Article;
use App\Services\QrcodeService;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Endroid\QrCode\QrCode;
use Endroid\QrCode\Writer\PngWriter;


class BasefrontController extends Controller
{
    /**
     * @Route("/basefront", name="basefront")
     */
    public function index(Request $request): Response
    {   //
        $allarticles = $this->getDoctrine()
            ->getRepository(Article::class)
            ->findAll();

        $articles = $this->get('knp_paginator')->paginate(
        // Doctrine Query, not results
            $allarticles,
            // Define the page parameter
            $request->query->getInt('page', 1),
            // Items per page
            3
        );


        return $this->render('basefront/index.html.twig', [
            'articles' => $articles,

        ]);

        //

    }
    /**
     * @Route("/basefront/{idArticle}/showArticle", name="showArticle", methods={"GET"})

     */
    public function showArticle(Article $article): Response
    {


        return $this->render('basefront/showArticle.html.twig', [
            'article' => $article,

        ]);
    }


}
