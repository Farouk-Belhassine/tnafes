<?php

namespace App\Repository;

use App\Entity\Utilisateur;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use Doctrine\ORM\Query\Expr\Join;


/**
 * @method Utilisateur|null find($id, $lockMode = null, $lockVersion = null)
 * @method Utilisateur|null findOneBy(array $criteria, array $orderBy = null)
 * @method Utilisateur[]    findAll()
 * @method Utilisateur[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class UtilisateurRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Utilisateur::class);
    }
    
    public function tableclientuser()
    {
        $q = $this->createQueryBuilder('u')
        ->innerJoin('App:Client', 'c', Join::WITH, 'u.id = c.idClient');
     
    return ($q->getQuery()->getResult());
    }

    public function tableclient()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT p FROM App:Client p ORDER BY p.idClient ASC'
            )
            ->getResult();
    }

    public function tablecoachuser()
    {
        $q = $this->createQueryBuilder('u')
        ->innerJoin('App:Coach', 'c', Join::WITH, 'u.id = c.id');
     
    return ($q->getQuery()->getResult());
    }

    public function tablecoach()
    {
        return $this->getEntityManager()
            ->createQuery(
                'SELECT p FROM App:Coach p ORDER BY p.id ASC'
            )
            ->getResult();
    }
}
