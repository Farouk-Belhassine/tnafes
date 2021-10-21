<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Form\CommentaireType;
use App\Repository\PublicationRepository;
use MercurySeries\FlashyBundle\FlashyNotifier;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 *
 * @Route("/commentaire")
 */
class CommentaireController extends AbstractController
{
    /**
     * @Route("/", name="commentaire_index", methods={"GET"})
     */
    public function index(NormalizerInterface $Normalizer): Response
    {
        $commentaires = $this->getDoctrine()
            ->getRepository(Commentaire::class)
            ->findAll();
        $jsoncontent =$Normalizer->normalize($commentaires,'json',['groups'=>'comment:read']);
        return $this->render('commentaire/index.html.twig', [
            'commentaires' => $commentaires,
            json_encode($jsoncontent),
        ]);
    }

    /**
     * @Route("/new", name="commentaire_new", methods={"GET","POST"})
     */
    public function new(Request $request,NormalizerInterface $Normalizer): Response
    {
        $commentaire = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($commentaire);
            $entityManager->flush();
            return $this->redirectToRoute('commentaire_index');
        }
        $jsoncontent =$Normalizer->normalize($commentaire,'json',['groups'=>'comment:read']);
        return $this->render('commentaire/index.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form->createView(),
            json_encode($jsoncontent),
        ]);
    }

    /**
     * @Route("/{idComment}", name="commentaire_show", methods={"GET"})
     */
    public function show(Commentaire $commentaire,NormalizerInterface $Normalizer): Response
    {    $jsoncontent =$Normalizer->normalize($commentaire,'json',['groups'=>'comment:read']);
        return $this->render('commentaire/show.html.twig', [
            'commentaire' => $commentaire,
            json_encode($jsoncontent),

        ]);
    }

    /**
     * @Route("/{idComment}/edit", name="commentaire_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Commentaire $commentaire,NormalizerInterface $Normalizer): Response
    {
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('commentaire_index');
        }
        $jsoncontent =$Normalizer->normalize($commentaire,'json',['groups'=>'comment:read']);
        return $this->render('commentaire/edit.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form->createView(),
            json_encode($jsoncontent),
        ]);
    }

    /**
     * @Route("/{idComment}", name="commentaire_delete", methods={"DELETE"})
     */
    public function delete(Request $request, Commentaire $commentaire,PublicationRepository $repository,FlashyNotifier $flashyNotifier): Response

    {
            //$repository=PublicationRepository::class;
            $id=$commentaire->getIdpub()->getIdPub();
            $publication=$repository->find($id);
            $nb=$publication->getNbcomment();
        if ($this->isCsrfTokenValid('delete'.$commentaire->getIdComment(), $request->request->get('_token'))) {
             $nb--;
             $publication->setNbcomment($nb);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($commentaire);
            $entityManager->flush();
        }
        $flashyNotifier->success('Commentaire supprimé avec succès','http://your-awesome-link.com');
        return $this->redirectToRoute('publication_index');
    }


    /**
     * @Route("/com/{idComment}", name="commentaire_delete2", methods={"DELETE"})
     */
    public function delete2(Request $request, Commentaire $commentaire,PublicationRepository $repository,FlashyNotifier $flashyNotifier): Response

    {
        //$repository=PublicationRepository::class;
        $id=$commentaire->getIdpub()->getIdPub();
        $publication=$repository->find($id);
        $nb=$publication->getNbcomment();
        if ($this->isCsrfTokenValid('delete'.$commentaire->getIdComment(), $request->request->get('_token'))) {
            $nb--;
            $publication->setNbcomment($nb);
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($commentaire);
            $entityManager->flush();
        }
        $flashyNotifier->success('Commentaire supprimé avec succès','http://your-awesome-link.com');
        return $this->redirectToRoute('publication_index2');
    }
}
