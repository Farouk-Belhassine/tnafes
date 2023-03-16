<?php

namespace App\Controller;

use App\Entity\Utilisateur;
use App\Entity\Client;
use App\Form\UtilisateurType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

/**
     * @Route("/signup", name="signup")
     */
class SignupController extends AbstractController
{
    /**
     * @Route("/", name="")
     */
    public function index(Request $request, UserPasswordEncoderInterface $passwordencoder, \Swift_Mailer $mailer): Response
    {
        $utilisateur = new Utilisateur();
        $formclient = $this->createForm(UtilisateurType::class, $utilisateur);
        $formclient->handleRequest($request);
        if($formclient->isSubmitted()&&$formclient->isValid()){
            $utilisateur->setRoles(["ROLE_CLIENT"]);
            $encoded = $passwordencoder->encodePassword($utilisateur, $utilisateur->getPassword());
            $utilisateur->setPassword($encoded);
            $em = $this->getDoctrine()->getManager();
            $em->persist($utilisateur);
            $em->flush();
            $client = new Client();
            $client->setIdClient($utilisateur);
            $em = $this->getDoctrine()->getManager();
            $em->persist($client);
            $em->flush();

            //envoi mail
            $message = (new \Swift_Message('Création de compte'))
            ->setFrom('tnafes.tnafes@gmail.com')
            ->setTo($utilisateur->getEmail())
            ->setBody(
                $this->renderView(
                    'mailregister.html.twig',
                    ['name' => $utilisateur->getNom()]
                ),
                'text/html'
            );
            $mailer->send($message);
            //fin envoi mail
            
            return $this->redirectToRoute('signup');
        }
        return $this->render('signup/index.html.twig', [
            'controller_name' => 'SignupController',
            'form' => $formclient->createView(),
        ]);
    }
}
