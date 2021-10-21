<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Stripe\Stripe;
use Symfony\Component\Routing\Generator\UrlGeneratorInterface;

class PaiementController extends AbstractController
{
    /**
     * @Route("/paiement", name="paiement")
     */
    public function index(): Response
    {
        return $this->render('paiement/index.html.twig', [
            'controller_name' => 'PaiementController',
        ]);
    }
    /**
     * @Route("/error", name="error")
     */
    public function error(){
        return $this->render('paiement/error.html.twig', [
        ]);
    }
    /**
     * @Route("/success", name="success")
     */
    public function success(){
        return $this->render('paiement/success.html.twig', [
        ]);
    }
    /**
     * @Route("/create-checkout-session", name="checkout")
     */
    public function checkout(): Response
    {
        \Stripe\Stripe::setApiKey('sk_test_51IijS6BAsMLI3bRGGi3rAfs1yolYvyYvNmSj26DOr8A4sv9fbkuhHWNMFXzy8aFJNH5ORQ48Tq69062Ycho9sw2h00Aq3apSDQ');
        $session = \Stripe\Checkout\Session::create([
            'payment_method_types' => ['card'],
            'line_items' => [[
                'price_data' => [
                    'currency' => 'usd',
                    'product_data' => [
                        'name' => 'Abonnement',
                    ],
                    'unit_amount' => 2000,
                ],
                'quantity' => 1,
            ]],
            'mode' => 'payment',
            'success_url' => $this->generateUrl('success',[],UrlGeneratorInterface::ABSOLUTE_URL),
            'cancel_url' => $this->generateUrl('error',[],UrlGeneratorInterface::ABSOLUTE_URL),
        ]);
        return new JsonResponse(['id'=> $session->id]);
    }
}
