<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Utilisateur;
use App\Entity\Client;
use App\Form\UtilisateurType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;

class AjoutclientController extends AbstractController
{
    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/ajoutclient", name="ajoutclient")
     */
    public function ajoutclient(Request $request, UserPasswordEncoderInterface $encoder, \Swift_Mailer $mailer): Response
    {
        $utilisateur = new Utilisateur();
        $formclient = $this->createForm(UtilisateurType::class, $utilisateur);
        $formclient->handleRequest($request);
        if($formclient->isSubmitted()&&$formclient->isValid()){
            $utilisateur->setRoles(["ROLE_CLIENT"]);
            $encoded = $encoder->encodePassword($utilisateur, $utilisateur->getPassword());
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

            return $this->redirectToRoute('affichagecoachclient');
        }
        return $this->render('ajoutclient/index.html.twig', [
            'controller_name' => 'AjoutclientController',
            'formclient' => $formclient->createView(),
        ]);
    }
}
