<?php


namespace App\Repository;


use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use App\Entity\Event;
/**

 * @method Event|null find($id, $lockMode = null, $lockVersion = null)
 * @method Event|null findOneBy(array $criteria, array $orderBy = null)
 * @method Event[]    findAll()
 * @method Event[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */

class EventRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Event::class);
    }
    /* public function count()
     {
         return $this->createQueryBuilder('r')
             ->select('count(r.idReclamation)')
             ->getQuery()
             ->getSingleScalarResult();
     }*/

    public function findeventparlieu($lieu){
        return $this->createQueryBuilder('e')
            ->where('e.lieu LIKE :lieu')
            ->setParameter('lieu', '%'.$lieu.'%')
            ->getQuery()
            ->getResult();
    }
   /* public function findrecByprenom($prenom){
        return $this->createQueryBuilder('r')
            ->where('r.prenomUser LIKE :prenom')
            ->setParameter('prenom', '%'.$prenom.'%')
            ->getQuery()
            ->getResult();
    }
*/

}