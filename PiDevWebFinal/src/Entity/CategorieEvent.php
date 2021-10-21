<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * CategorieEvent
 *
 * @ORM\Table(name="categorie_event")
 * @ORM\Entity
 * @UniqueEntity("thematique")
 */
class CategorieEvent
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_categ_event", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idCategEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="thematique", type="string", length=20, nullable=false)
     *  @Assert\NotBlank(message="Thematique obligatoire")
     */
    private $thematique;

    /**
     * @return int
     */
    public function getIdCategEvent(): int
    {
        return $this->idCategEvent;
    }

    /**
     * @param int $idCategEvent
     */
    public function setIdCategEvent(int $idCategEvent): void
    {
        $this->idCategEvent = $idCategEvent;
    }

    /**
     * @return string
     */
    public function getThematique(): ?string
    {
        return $this->thematique;
    }

    /**
     * @param string $thematique
     */
    public function setThematique(string $thematique): void
    {
        $this->thematique = $thematique;
    }
   public function __toString() {
      return $this->thematique;
    }
    /**
     * @ORM\OneToMany(targetEntity=Event::class,mappedBy="category",cascade={"all"})
     */
    private $evenement;


}
