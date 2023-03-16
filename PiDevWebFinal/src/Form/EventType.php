<?php

namespace App\Form;

use App\Entity\CategorieEvent;
use App\Entity\Event;
//use Doctrine\DBAL\Types\DateTimeType;
use phpDocumentor\Reflection\Type;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Vich\UploaderBundle\Form\Type\VichImageType;


class EventType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            //->add('idCoach')
            ->add('lieu', ChoiceType::class, [

                'choices'  => [

                    'Bizerte'=>'Bizerte',
                    'Sousse'=>'Sousse' ,
                    'Hamammet'=>'Hamammet',],
])
            ->add('nbPlace')
            ->add('dateEvent', DateType::class,[ 'placeholder' => [
                'year' => 'Year', 'month' => 'Month', 'day' => 'Day',]])
          ->add('descriptions',TextType::class, ['label'=>'description'])

          ->add('imageFile', VichImageType::class,[
             'attr'=>['placeholder'=>'merci de telecharger une image'],
              'allow_delete' => true,
              'download_link' => false,
              'label'=>false
          ] )


            ->add('category',EntityType::class,['class' => CategorieEvent::class,
             'choice_label' => 'thematique'])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Event::class,
        ]);
    }
}
