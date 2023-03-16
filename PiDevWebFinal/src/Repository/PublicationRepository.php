<?php


namespace App\Repository;


use App\Entity\Publication;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
/**

* @method Publication|null find($id, $lockMode = null, $lockVersion = null)
* @method Publication|null findOneBy(array $criteria, array $orderBy = null)
 * @method Publication[]    findAll()
* @method Publication[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
*/
class PublicationRepository extends ServiceEntityRepository
{

    /**
     * PublicationRepository constructor.
     */
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Publication::class);
    }
    public  function  triedecroissant()
    {
        return $this->createQueryBuilder('pub')
            ->orderBy('pub.datePublication','DESC')
            ->getQuery()
            ->getResult();
    }
    public function nbComments($id){
        return $this->createQueryBuilder('pub')
               ->join('pub.idPub','comment')
               ->addSelect('count(comment.idComment')
               ->where('comment.idpub=:id')
               ->setParameter('id',$id)
               ->getQuery()
               ->getResult();

    }
    public  function  pubcomm($id)
    {
        return $this->createQueryBuilder('pub')
            ->select('pub')
            ->where('pub.idPub=:id')
            ->getQuery()
            ->getResult();
    }
    public  function  trie3()
    {
        return $this->createQueryBuilder('pub')
            ->setMaxResults(3)
            ->orderBy('pub.datePublication','DESC')
            ->getQuery()
            ->getResult();
    }
}