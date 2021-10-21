<?php

namespace App\Controller;

use App\Entity\Reclamation2;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Component\Validator\Constraints\Json;
use Doctrine\ORM\EntityManagerInterface;
class ReclamationJsonController extends AbstractController
{
    /*json*/
    /**
     * @Route("/affichereclam", name="reclam_json")
     */
    public function liste_json( NormalizerInterface $serializer)
    {
        $repository = $this->getDoctrine()->getRepository(Reclamation2::class);

        $reclamation=$repository->findAll();

        $jsoncontentc =$serializer->normalize($reclamation,"json",['groups'=>'reclamation']);
        dump($jsoncontentc);
        $jsonc=json_encode($jsoncontentc);
        dump($jsonc);
        return new JsonResponse($jsoncontentc);

    }
    /**
     * @Route("/jsonAjoutreclam", name="reclam_json_ajout",methods={"GET","POST"})
     *
     */
    public function ajout_json(Request $request)
    {
       /* $reclamation = new Reclamation();
        $em=$this->getDoctrine()->getManager();
        $em->persist($reclamation);
        $em->flush();
        $jsoncontentc =$serializer->normalize($reclamation,'json',['groups'=>'reclamation']);

        return new Response(json_encode($jsoncontentc));
      */


        $reclamation = new Reclamation2();
        $description = $request->query->get("description");
        $objet = $request->query->get("objet");


        $date = new \DateTime('now');
        $em = $this->getDoctrine()->getManager();

        $reclamation->setObjet($objet);
        $reclamation->setDescription($description);
        $reclamation->setDate($date);
        $reclamation->setEtat("Non traitée");


        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation,"json",['groups'=>'reclamation']);
        return new JsonResponse($formatted);

    }

    /**
     * @Route("/editjson", name="reclam_json_modif")
     * @Method ("PUT")
     */
    public function modif_json(Request $request )
    {
        $em = $this->getDoctrine()->getManager();
        $reclamation = $em->getRepository(Reclamation2::class)->find($request->get("id_reclamation"));
        $reclamation->setEtat($request->get("etat"));
        $em->persist($reclamation);
        $em->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($reclamation);
        return new JsonResponse("Reclamation a ete modifiee avec success.");
       // return new JsonResponse($formatted);

    }
}
