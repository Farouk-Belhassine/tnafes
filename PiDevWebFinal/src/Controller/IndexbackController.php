<?php

namespace App\Controller;

use App\Entity\Abonnement;
use App\Entity\Activite;
use App\Repository\ReclamationRepository;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class IndexbackController extends AbstractController
{
    /**
     * @Route("/indexback", name="indexback")
     */
    public function index(ReclamationRepository $repository,IndexbackController $indexbackController): Response
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

        $reclamations=$repository->countByDate();
        $dates=[];
        $reclamationCount=[];
        foreach($reclamations as $reclamation){
            $dates[] = $reclamation['dateReclam'];
            $reclamationCount[] = $reclamation['count'];
        }
        return $this->render('indexback/index.html.twig', [
            'dates' => json_encode($dates),
            'reclamationCount' => json_encode($reclamationCount),
            'users' => $indexbackController->stats(),
            'activites' => $activites,
            'piechart' => $pieChart
        ]);
    }
    public function stats()
    {
        $abonnements = $this->getDoctrine()
            ->getRepository(Abonnement::class);
        return $abonnements->createQueryBuilder('u')
            ->Select('COUNT(u.type) as pff')
            ->addSelect('u.type')
            ->groupBy('u.type')
            ->getQuery()
            ->getResult()
            ;
    }
}
