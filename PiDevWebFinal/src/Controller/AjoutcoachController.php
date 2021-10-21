<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Entity\Utilisateur;
use App\Entity\Coach;
use App\Form\CoachType;
use App\Form\UtilisateurType;
use Symfony\Component\HttpFoundation\Request;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;

class AjoutcoachController extends AbstractController
{
    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/ajoutcoach", name="ajoutcoach")
     */
    public function ajoutcoach(Request $request, UserPasswordEncoderInterface $encoder, \Swift_Mailer $mailer): Response
    {
        $utilisateur = new Utilisateur();
        $coach = new Coach();
        $formcoach = $this->createForm(UtilisateurType::class, $utilisateur);
        $formcoache = $this->createForm(CoachType::class, $coach);
        $formcoach->handleRequest($request);
        $formcoache->handleRequest($request);
        if($formcoach->isSubmitted()&&$formcoach->isValid()){
            $utilisateur->setRoles(["ROLE_COACH"]);
            $encoded = $encoder->encodePassword($utilisateur, $utilisateur->getPassword());
            $utilisateur->setPassword($encoded);
            $em = $this->getDoctrine()->getManager();
            $em->persist($utilisateur);
            $em->flush();
            $coach->setId($utilisateur);
            $em = $this->getDoctrine()->getManager();
            $em->persist($coach);
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

            return $this->redirectToRoute('ajoutcoach');
        }
        return $this->render('ajoutcoach/index.html.twig', [
            'controller_name' => 'AjoutcoachController',
            'formcoach' => $formcoach->createView(),
            'formcoache' => $formcoache->createView(),
        ]);
    }
}
