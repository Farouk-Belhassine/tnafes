<?php

namespace App\Form;

use App\Entity\Reclamation;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Validator\Constraints\Date;

class ReclamationType2 extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nomUser',TextType::class,[
                'disabled'=>true
            ])
            ->add('prenomUser',TextType::class,[
                'disabled'=>true
            ])
            ->add('email',TextType::class,[
                'disabled'=>true
            ])
            ->add('numtel',TextType::class,[
                'disabled'=>true
            ])
            ->add('description',TextType::class,[
                'disabled'=>true
            ])
            ->add('objet',TextType::class,[
                'disabled'=>true
            ])
            ->add('date',DateType::class,[
                'disabled'=>true
            ])
            ->add('etat',ChoiceType::class,[
                'choices'=>[
                   'Traitée'=>'Traitée',
                    'En traitement'=>'En traitement',
                ]
            ])

            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptchaUserRegistration'
            ))
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
