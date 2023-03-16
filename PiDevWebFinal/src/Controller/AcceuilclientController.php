<?php

namespace App\Controller;

use App\Repository\PublicationRepository;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class AcceuilclientController extends AbstractController
{
    /**
     * @Route("/acceuilclient", name="acceuilclient")
     */
    public function index(Request $request,PaginatorInterface $paginator,PublicationRepository $repository): Response
    {

        $donnees =$repository->findAll();
        $publications=$paginator->paginate(
            $donnees,// Requête contenant les données à paginer (ici les publications)
            $request->query->getInt('page',1),// Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            3   // Nombre de résultats par page
        );
        return $this->render('publication/index2.html.twig', [
            'controller_name' => 'AcceuilclientController',
            'publications'=>$publications,
        ]);
    }
}
