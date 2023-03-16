<?php

namespace App\Form;

use App\Entity\DateTimeInterface;
use App\Entity\Abonnement;
use App\Entity\Utilisateur;
use Doctrine\DBAL\Types\TextType;
use phpDocumentor\Reflection\Type;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FormType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;

use Symfony\Component\Form\Extension\Core\Type\DateType;


class AbonnementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('dateDebut',DateType::class)
            ->add('dateFin',DateType::class)
            ->add('type',ChoiceType::class, array(
                'choices'  => array(
                    'Abonnement annuel' => 'Abonnement annuel',
                    'Abonnement mensuel' => 'Abonnnement mensuel',

                ),
            ))
            ->add('idUtilisateur',EntityType::class,[
                'class'=>Utilisateur::class,
                'choice_label'=>'id',
            ])

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Abonnement::class,
        ]);
    }
}
