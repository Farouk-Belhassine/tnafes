<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Form\UtilisateurType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\HttpFoundation\RedirectResponse;

class UpdatecurrentuserdataController extends AbstractController
{
    /**
     * @Route("/updatecurrentuserdata", name="updatecurrentuserdata")
     */
    public function index(Request $request, UserPasswordEncoderInterface $encoder): Response
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $form = $this->createForm(UtilisateurType::class, $user);
        $form->handleRequest($request);
        if($form->isSubmitted()&&$form->isValid()){
            $em = $this->getDoctrine()->getManager();
            $user = $form->getData();
            $encoded = $encoder->encodePassword($user, $user->getPassword());
            $user->setPassword($encoded);
            $em->flush();

            $roles = $user->getRoles();
            if ($roles==["ROLE_ADMIN"]){
                $redirection = new RedirectResponse('affichagecoachclient');
            }
            // kenou coach
            /*elseif($roles==["ROLE_COACH"]){
                $redirection = new RedirectResponse('menucoach');
            }*/
            // kenou client
            else{
                $redirection = new RedirectResponse('acceuilclient');
            }
        
            return $redirection;
            
        }
        return $this->render('updatecurrentuserdata/index.html.twig', [
            'controller_name' => 'UpdatecurrentuserdataController',
            'form' => $form->createView(),
            'user' => $user,
        ]);
    }
}
