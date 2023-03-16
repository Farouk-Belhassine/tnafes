<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

//on rajoute ce use pour contrainte d'unicité
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Annotation\Groups;
//use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 * @UniqueEntity("nomCategorie",message="Une activité existe déja avec ce titre") //le champ unique
 */
class Categorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_categorie", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups ("Categorie")
     */
    private $idCategorie;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_categorie", type="string", length=20, nullable=false)
     * @Assert\NotBlank(message="Nom de catégorie obligatoire")
     * @Assert\Length(
     *      min = 3,
     *      max = 10,
     *      minMessage = "le nom de catégorie doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "le nom de catégorie ne doit pas dépasser {{ limit }} caractères"
     * )
     * @Groups ("Categorie")
     */
    private $nomCategorie;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=100, nullable=false)
     * @Assert\NotBlank(message="Description est obligatoire")
     * @Assert\Length(
     *      min = 10,
     *      max = 150,
     *      minMessage = "la description doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "la description ne doit pas dépasser {{ limit }} caractères"
     * )
     * @Groups ("Categorie")
     */
    private $description;

    /**
     * @return int
     */
    public function getIdCategorie(): ?int
    {
        return $this->idCategorie;
    }

    /**
     * @param int $idCategorie
     */
    public function setIdCategorie(int $idCategorie): void
    {
        $this->idCategorie = $idCategorie;
    }

    /**
     * @return string
     */
    public function getNomCategorie(): ?string
    {
        return $this->nomCategorie;
    }

    /**
     * @param string $nomCategorie
     */
    public function setNomCategorie(string $nomCategorie): void
    {
        $this->nomCategorie = $nomCategorie;
    }

    /**
     * @return string
     */
    public function getDescription(): ?string
    {
        return $this->description;
    }

    /**
     * @param string $description
     */
    public function setDescription(string $description): void
    {
        $this->description = $description;
    }

#pour lister les categories dans le choice label
    public function __toString() {
        return $this->nomCategorie;
    }
}