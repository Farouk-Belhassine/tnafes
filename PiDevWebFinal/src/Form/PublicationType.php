<?php

namespace App\Form;

use App\Entity\Publication;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Vich\UploaderBundle\Form\Type\VichImageType;

class PublicationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    { $builder
            ->add('contenu',TextareaType::class)
            //->add('urlimage')
            ->add('imageFile',VichImageType::class,[
                'allow_delete' => true,
                'download_link' => false,
                'label'=>false,
                'attr'=>
                    ['placeholder'=>'Choisir une image',
                        'button_label'=>'Importer']

            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Publication::class,
        ]);
    }
}
