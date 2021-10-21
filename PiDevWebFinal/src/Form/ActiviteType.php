<?php

namespace App\Form;

use App\Entity\Activite;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ActiviteType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('titre')
            #->add('date')
            ->add('description')
            #->add('nomcat')
            ->add('idCategorie')
            #->add('idCategorie', EntityType::class, [
                #'class' => Categorie::class,
                #'choice_label' => 'nomCategorie',
            #])
            #->add('idCoachact')
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Activite::class,
        ]);
    }
}
