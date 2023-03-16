<?php

namespace App\Controller;

use App\Entity\Client;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\UtilisateurRepository;
use App\Entity\Utilisateur;
use App\Entity\Coach;
use Symfony\Component\HttpFoundation\Request;
use App\Form\ClienType;
use App\Form\CoachType;
use App\Form\UtilisateurType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/affichagecoachclient", name="affichagecoachclient")
     */
class AffichagecoachclientController extends AbstractController
{
    /**
     * @Route("/", name="")
     */
    public function index(UtilisateurRepository $UtilisateurRepository): Response
    {
        $clientsuser = $UtilisateurRepository->tableclientuser();
        $clients = $UtilisateurRepository->tableclient();
        $coachsuser = $UtilisateurRepository->tablecoachuser();
        $coachs = $UtilisateurRepository->tablecoach();
        

        return $this->render('affichagecoachclient/index.html.twig', [
            'controller_name' => 'AffichagecoachclientController',
            'clientsuser' => $clientsuser,
            'clients' => $clients,
            'coachsuser' => $coachsuser,
            'coachs' => $coachs
        ]);
    }

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function remove(Utilisateur $utilisateur)
    {
        $em=$this->getDoctrine()->getManager();
        $em->remove($utilisateur);
        $em->flush();

        return $this->redirectToRoute('affichagecoachclient');
    }

    /**
     * @Route("/updatecoach/{id}", name="updatecoach")
     */
    public function updatecoach(Request $request, Utilisateur $utilisateur, Coach $coach)
    {
        $formcoach = $this->createForm(UtilisateurType::class, $utilisateur);
        $formcoache = $this->createForm(CoachType::class, $coach);
        $formcoach->handleRequest($request);
        $formcoache->handleRequest($request);
        if($formcoach->isSubmitted()&&$formcoach->isValid()){
            $em = $this->getDoctrine()->getManager();
            $em->remove($coach);
            $em->flush();
            $em = $this->getDoctrine()->getManager();
            $coach->setId($utilisateur);
            $em->persist($coach);
            $em->flush();
            
            return $this->redirectToRoute('affichagecoachclient');
        }
        return $this->render('modifcoach/index.html.twig', [
            'controller_name' => 'affichagecoachclientController',
            'formcoach' => $formcoach->createView(),
            'formcoache' => $formcoache->createView(),
        ]);
    }

    /**
     * @Route("/bloque/{id}", name="bloque")
     */
    public function bloque(Request $request, Client $client, \Swift_Mailer $mailer, UtilisateurRepository $UtilisateurRepository)
    {
        if($client->getDateblock()!=null){
            $em = $this->getDoctrine()->getManager();
            $client = $this->createForm(ClienType::class, $client)->getData();
            $client->setPermaban(0);
            $client->setDateblock(null);
            $em->flush();

            return $this->redirectToRoute('affichagecoachclient');
        }
        else{
            $formclient = $this->createForm(ClienType::class, $client);
            $formclient->handleRequest($request);
            if($formclient->isSubmitted()&&$formclient->isValid()){
                $em = $this->getDoctrine()->getManager();
                $client = $formclient->getData();
                $client->setPermaban(0);
                $em->flush();

                $user = $UtilisateurRepository->find($client->getIdClient());
                //envoi mail
                $message = (new \Swift_Message('Bloque temporaire'))
                ->setFrom('tnafes.tnafes@gmail.com')

                ->setTo($user->getEmail())
                ->setBody(
                $this->renderView(
                    'mailbloque.html.twig',[
                    'name' => $user->getNom(),
                    'dateblock' => $client->getDateblock(),
                ]),
                'text/html'
            );
            $mailer->send($message);
            //fin envoi mail
            
                return $this->redirectToRoute('affichagecoachclient'); 
            }
            return $this->render('bloque.html.twig', [
                'controller_name' => 'affichagecoachclientController',
                'formclient' => $formclient->createView(),
            ]);
        }
    }

    /**
     * @Route("/ban/{id}", name="ban")
     */
    public function ban(Client $client, \Swift_Mailer $mailer, UtilisateurRepository $UtilisateurRepository)
    {
        $em = $this->getDoctrine()->getManager();
        $client = $this->createForm(ClienType::class, $client)->getData();
        if($client->getPermaban()==false){$client->setPermaban(1);}
        else{$client->setPermaban(0);}
        $client->setDateblock(null);
        $em->flush();

        $user = $UtilisateurRepository->find($client->getIdClient());
                //envoi mail
                $message = (new \Swift_Message('Ban'))
                ->setFrom('tnafes.tnafes@gmail.com')

                ->setTo($user->getEmail())
                ->setBody(
                $this->renderView(
                    'mailban.html.twig',[
                    'name' => $user->getNom(),
                ]),
                'text/html'
            );
            $mailer->send($message);
            //fin envoi mail

        return $this->redirectToRoute('affichagecoachclient');
    }
}
