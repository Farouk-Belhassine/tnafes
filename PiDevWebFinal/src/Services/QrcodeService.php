<?php


namespace App\Services;
use Endroid\QrCode\Builder\BuilderInterface;
use Endroid\QrCode\Encoding\Encoding;
use Endroid\QrCode\ErrorCorrectionLevel\ErrorCorrectionLevelHigh;


class QrcodeService
{
    /**
     * @var BuilderInterface
     */
    protected $builder;
 public function __construct(BuilderInterface $builder){
$this->builder =$builder;
 }

 public function qrcode($query){
     $url ='https://fr.wikipedia.org/wiki/';

     $result= $this->builder
         ->data($url.$query)
         ->encoding(new Encoding('UTF-8'))
         ->errorCorrectionLevel(new ErrorCorrectionLevelHigh())
         ->size(400)
         ->margin(10)
         ->build()
         ;
     return $result;
 }

}