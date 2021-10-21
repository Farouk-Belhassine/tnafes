<?php

namespace App\Controller;

use App\Entity\Activite;
use App\Form\ActiviteType;
use CMEN\GoogleChartsBundle\GoogleCharts\Options\Months;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;

// Include Dompdf required namespaces
use Dompdf\Dompdf;
use Dompdf\Options;

/**
 * @Route("/activite")
 */
class ActiviteController extends AbstractController
{
    /**
     * @Route("/", name="activite_index", methods={"GET"})
     */
    public function index(): Response
    {
        $activites = $this->getDoctrine()
            ->getRepository(Activite::class)
            ->findAll();

        $pieChart = new PieChart();
        //les variables des mois
        $jan = 0; $fev = 0; $mar = 0; $apr = 0; $mai = 0; $jui = 0; $juil = 0; $aug = 0; $sep = 0; $oct = 0; $nov = 0; $dec = 0;

        foreach ($activites as $a) {
            $cat = $a->getDate()->format('M');

            if ($cat == 'Jan') {
                $jan ++;
            }
            elseif ($cat == 'Feb'){
                $fev++;
            }
            elseif ($cat == 'Mar'){
                $mar++;
            }
            elseif ($cat == 'Apr'){
                $apr++;
            }
            elseif ($cat == 'May'){
                $mai++;
            }
            elseif ($cat == 'Jun'){
                $jui++;
            }
            elseif ($cat == 'Jul'){
                $juil++;
            }
            elseif ($cat == 'Aug'){
                $aug++;
            }
            elseif ($cat == 'Sep'){
                $sep++;
            }
            elseif ($cat == 'Oct'){
                $oct++;
            }
            elseif ($cat == 'Nov'){
                $nov++;
            }
            else
                $dec++;

        }

            $pieChart->getData()->setArrayToDataTable(

                [['Task', 'Hours per Day'],
                    ['janvier', $jan],
                    ['février', $fev],
                    ['mars', $mar],
                    ['avril', $apr],
                    ['mai', $mai],
                    ['juin', $jui],
                    ['juillet', $juil],
                    ['août', $aug],
                    ['septembre', $sep],
                    ['octobre', $oct],
                    ['novembre', $nov],
                    ['décembre', $dec]

                ]
            );

        $pieChart->getOptions()->setTitle('Activitiés par mois');
        $pieChart->getOptions()->setHeight(700);
        $pieChart->getOptions()->setWidth(900);
        $pieChart->getOptions()->getTitleTextStyle()->setBold(true);
        $pieChart->getOptions()->getTitleTextStyle()->setColor('#ffccdc');
        $pieChart->getOptions()->getTitleTextStyle()->setItalic(true);
        $pieChart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $pieChart->getOptions()->getTitleTextStyle()->setFontSize(35);
        $pieChart->getOptions()->getBackgroundColor()->setFill('transparent');

        //return $this->render('AppBundle::activite/stat.html.twig', array('piechart' => $pieChart));


        return $this->render('activite/index.html.twig', [
            'activites' => $activites,
            'piechart' => $pieChart
        ]);
    }


    /**
     * @Route("/listA", name="activite_list", methods={"GET"})
     */
    public function listA(): Response
    {
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $activites = $this->getDoctrine()
            ->getRepository(Activite::class)
            ->findAll();



        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('activite/listA.html.twig', [
            'activites' => $activites,

        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);


    }


    /**
     * @Route("/new", name="activite_new", methods={"GET","POST"})
     */
    public function new(Request $request): Response
    {
        $activite = new Activite();
        $activite->setDate(new \DateTime('now'));
        $form = $this->createForm(ActiviteType::class, $activite);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {  //isValid() pour tenir compte des Assert au niveau du controle de saisie
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->persist($activite);
            $entityManager->flush();


            return $this->redirectToRoute('activite_index');
        }

        return $this->render('activite/new.html.twig', [
            'activite' => $activite,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idActivite}", name="activite_show", methods={"GET"})
     */
    public function show(Activite $activite): Response
    {
        return $this->render('activite/show.html.twig', [
            'activite' => $activite,
        ]);
    }

    /**
     * @Route("/{idActivite}/edit", name="activite_edit", methods={"GET","POST"})
     */
    public function edit(Request $request, Activite $activite): Response
    {
        $form = $this->createForm(ActiviteType::class, $activite);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $this->getDoctrine()->getManager()->flush();

            return $this->redirectToRoute('activite_index');
        }

        return $this->render('activite/edit.html.twig', [
            'activite' => $activite,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idActivite}", name="activite_delete", methods={"POST"})
     */
    public function delete(Request $request, Activite $activite): Response
    {
        if ($this->isCsrfTokenValid('delete'.$activite->getIdActivite(), $request->request->get('_token'))) {
            $entityManager = $this->getDoctrine()->getManager();
            $entityManager->remove($activite);
            $entityManager->flush();
        }

        return $this->redirectToRoute('activite_index');
    }




}
