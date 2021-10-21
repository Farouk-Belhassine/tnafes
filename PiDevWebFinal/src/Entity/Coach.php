<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Coach
 *
 * @ORM\Table(name="coach")
 * @ORM\Entity
 */
class Coach
{
    /**
     * @var string
     *
     * @ORM\Column(name="role", type="string", length=20, nullable=false)
     * @Assert\NotBlank (message="role obligatoire!")
     */
    private $role;

    /**
     * @var int
     *
     * @ORM\Column(name="salaire", type="integer", nullable=false)
     * @Assert\NotBlank (message="salaire obligatoire!")
     */
    private $salaire;

    /**
     * @var \Utilisateur
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_coach", referencedColumnName="id")
     * })
     */
    private $id;

    public function getId(){return $this->id;}
    public function getRole(){return $this->role;}
    public function getSalaire(){return $this->salaire;}

    public function setId(?Utilisateur $id){$this->id = $id;}
    public function setRole(string $role){$this->role = $role;}
    public function setSalaire(int $salaire){$this->salaire = $salaire;}
}
