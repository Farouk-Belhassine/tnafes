<?php

namespace App\Form;

use App\Entity\Participation;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Captcha\Bundle\CaptchaBundle\Form\Type\CaptchaType;
use App\Entity\Utilisateur;

class ParticipationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {


        $builder
            ->add('nom',TextType::class,[
                'disabled'=>true,

            ])
            ->add('prenom',TextType::class,[
                'disabled'=>true
            ])
            ->add('eMail',TextType::class,[
                'disabled'=>true
            ])
        /* ->add('nbParticipant')
            ->add('datePart')
            ->add('dateEvenement')
            ->add('idEvenement')
            ->add('idClient')*/
            ->add('captchaCode', CaptchaType::class, array(
                'captchaConfig' => 'ExampleCaptchaUserRegistration'
            ));

    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Participation::class,
        ]);
    }
}

