<?php

namespace App\Controller;

use App\Entity\Article;
use App\Form\ArticleType;
use App\Repository\ArticleRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
//use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/article")
 */
class ArticleController extends AbstractController
{
    /**
     * @Route("/", name="article_index", methods={"GET"})
     */
    public function index(): Response
    {
        $articles = $this->getDoctrine()
            ->getRepository(Article::class)
            ->findAll();

        return $this->render('article/index.html.twig', [
            'articles' => $articles,
        ]);
    }



    /**
     * @Route("/new", name="article_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $article = new Article();
        $article->setDate(new \DateTime('now'));
        $form = $this->createForm(ArticleType::class, $article);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($article);
            $entityManager->flush();

            return $this->redirectToRoute('article_index');
        }

        return $this->render('article/new.html.twig', [
            'article' => $article,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idArticle}", name="article_show", methods={"GET"})
     */
    public function show(Article $article): Response
    {
        return $this->render('article/show.html.twig', [
            'article' => $article,
        ]);
    }





    /**
     * @Route("/{idArticle}/edit", name="article_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Article $article): Response
    {
        $form = $this->createForm(ArticleType::class, $article);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('article_index');
        }

        return $this->render('article/edit.html.twig', [
            'article' => $article,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idArticle}", name="article_delete", methods={"POST"})
     */
    public function delete(Request $request, Article $article): Response
    {
        if ($this->isCsrfTokenValid('delete'.$article->getIdArticle(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($article);
            $entityManager->flush();
        }

        return $this->redirectToRoute('article_index');
    }



    /**
     * @Route("/s/searchArticlex", name="searchArticlex")
     */
    public function searchArticle(Request $request,NormalizerInterface $Normalizer,ArticleRepository $repository):Response
    {
//$repository = $this->getDoctrine()->getRepository(Article::class);
        $requestString=$request->get('searchValue');
        $articles = $repository->findpartitre($requestString);
        $jsonContent = $Normalizer->normalize($articles, 'json',['Groups'=>'articles:read']);
        $retour =json_encode($jsonContent);

        return new Response($retour);

    }


    //MOBILE

    /**
     * @Route ("/a/allart", name="allart")
     */
    public function allArt(NormalizerInterface $normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Article::class);
        $articles = $repository->findAll();
        $jsonContent = $normalizer->normalize($articles, 'json',['Groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    /**
     * @Route ("/a/deleteartJSON/{idArticle}", name="deleteartJSON", methods={"GET","POST"})
     */
    public function deleteCatJSON(Request $request,Article $art,NormalizerInterface $normalizer)
    {
        $em= $this->getDoctrine()->getManager();
        $article = $em->getRepository(Article::class)->find($art->getIdArticle());
        $em->remove($article);
        $em->flush();
        $jsonContent=$normalizer->normalize($article,'json',['Gropus'=>'post:read']);
        return new Response("Article supprimé".json_encode($jsonContent));

    }


}
