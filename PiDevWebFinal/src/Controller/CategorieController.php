<?php

namespace App\Controller;

use App\Entity\Categorie;
use App\Form\CategorieType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/categorie")
 */
class CategorieController extends AbstractController
{
    /**
     * @Route("/", name="categorie_index", methods={"GET"})
     */
    public function index(): Response
    {
        $categories = $this->getDoctrine()
            ->getRepository(Categorie::class)
            ->findAll();

        return $this->render('categorie/index.html.twig', [
            'categories' => $categories,
        ]);
    }

    /**
     * @Route("/new", name="categorie_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($categorie);
            $entityManager->flush();

            return $this->redirectToRoute('categorie_index');
        }

        return $this->render('categorie/new.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCategorie}", name="categorie_show", methods={"GET"})
     */
    public function show(Categorie $categorie): Response
    {
        return $this->render('categorie/show.html.twig', [
            'categorie' => $categorie,
        ]);
    }

    /**
     * @Route("/{idCategorie}/edit", name="categorie_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Categorie $categorie): Response
    {
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('categorie_index');
        }

        return $this->render('categorie/edit.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCategorie}", name="categorie_delete", methods={"POST"})
     */
    public function delete(Request $request, Categorie $categorie): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorie->getIdCategorie(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($categorie);
            $entityManager->flush();
        }

        return $this->redirectToRoute('categorie_index');
    }

// MOBILE
    /**
     * @Route("/c/liste3", name="liste3")
     */
    public function getallcategories3()
    {
        $cat = $this->getDoctrine()->getManager()->getRepository(Categorie::class)->findAll();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $categorie = $serializer->normalize($cat);
        //return  new Response(json_encode($json));

        return  new Response(json_encode($categorie));
    }
    /**
     * @Route ("/c/allcat", name="allcat")
     */
    public function allCat(NormalizerInterface $normalizer)
    {
        $repository = $this->getDoctrine()->getRepository(Categorie::class);
        $categories = $repository->findAll();
        $jsonContent = $normalizer->normalize($categories, 'json',['Groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }


    /**
     * @Route("/c/{idCategorie}", name="categorie_id", methods={"GET"})
     */
    public function categorieId(Request $request,Categorie $cat, NormalizerInterface $normalizer)
    {
        $em = $this->getDoctrine()->getManager();
        $categorie = $em->getRepository(Categorie::class)->find($cat->getIdCategorie());
        $jsonContent = $normalizer->normalize($categorie, 'json',['Groups'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    //testez l'ajout
//http://127.0.0.1:8000/categorie/c/addcatJSON/new?nomCategorie=json&description=jejejeson
    /**
     * @Route ("/c/addcatJSON/new", name="addcatJSON")
     */
    public function addCatJSON(Request $request,NormalizerInterface $normalizer)
    {
        $em=$this->getDoctrine()->getManager();
        $categorie = new Categorie();
        $categorie->setNomCategorie($request->get('nomCategorie'));
        $categorie->setDescription($request->get('description'));
        $em->persist($categorie);
        $em->flush();
        $jsonContent=$normalizer->normalize($categorie,'json',['Gropus'=>'post:read']);
        return new Response(json_encode($jsonContent));
    }

    //testez la modification
    //http://127.0.0.1:8000/categorie/c/updatecatJSON/45?nomCategorie=tason&description=ttttttt
    /**
     * @Route ("/c/updatecatJSON/{nomCategorie}", name="updatecatJSON")
     */
    public function updateCatJSON(Request $request,$nomCategorie,NormalizerInterface $normalizer)
    {
        $em= $this->getDoctrine()->getManager();
        $categorie = $em->getRepository(Categorie::class)->findOneBy(array('nomCategorie' => $nomCategorie ));
        $categorie->setNomCategorie($request->get('nomCategorie'));
        $categorie->setDescription($request->get('description'));
        $em->flush();
        $jsonContent=$normalizer->normalize($categorie,'json',['Gropus'=>'post:read']);
        return new Response("Categorie modifiée".json_encode($jsonContent));

    }

    /**
     * @Route ("/c/deletecatJSON/{idCategorie}", name="deletecatJSON", methods={"GET","POST"})
     */
    public function deleteCatJSON(Request $request,Categorie $cat,NormalizerInterface $normalizer)
    {
        $em= $this->getDoctrine()->getManager();
        $categorie = $em->getRepository(Categorie::class)->find($cat->getIdCategorie());
        $em->remove($categorie);
        $em->flush();
        $jsonContent=$normalizer->normalize($categorie,'json',['Gropus'=>'post:read']);
        return new Response("Categorie supprimée".json_encode($jsonContent));

    }

}
