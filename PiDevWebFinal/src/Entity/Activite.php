<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

//on rajoute ce use pour contrainte d'unicité
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * Activite
 *
 * @ORM\Table(name="activite", indexes={@ORM\Index(name="fk_idcat", columns={"id_categorie"}), @ORM\Index(name="fk_idcoach_act", columns={"id_coachact"})})
 * @ORM\Entity
 * @UniqueEntity("titre",message="Une activité existe déja avec ce titre") //le champ unique
 */
class Activite
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_activite", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idActivite;

    /**
     * @var string
     *
     * @ORM\Column(name="titre", type="string", length=20, unique=true, nullable=false)
     * @Assert\NotBlank(message="Titre obligatoire")
     * @Assert\Length(
     *      min = 3,
     *      max = 10,
     *      minMessage = "le titre doit contenir au moins {{ limit }} caractères",
     *      maxMessage = "le titre ne doit pas dépasser {{ limit }} caractères"
     * )
     */
    private $titre;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     */
    private $date;

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
     */
    private $description;

    /**
     * @var string|null
     *
     * @ORM\Column(name="nomcat", type="string", length=20, nullable=true, options={"default"="NULL"})
     */
    private $nomcat = 'NULL';

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity=Categorie::class,inversedBy="idCategorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_categorie", referencedColumnName="id_categorie")
     * })
     * @Assert\NotBlank(message="choix de catégorie est obligatoire")
     */
    private $idCategorie;

    /**
     * @var \Coach
     *
     * @ORM\ManyToOne(targetEntity="Coach")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_coachact", referencedColumnName="id_coach")
     * })
     */
    private $idCoachact;

    /**
     * @return int
     */
    public function getIdActivite(): int
    {
        return $this->idActivite;
    }

    /**
     * @param int $idActivite
     */
    public function setIdActivite(int $idActivite): void
    {
        $this->idActivite = $idActivite;
    }

    /**
     * @return string
     */
    public function getTitre(): ?string
    {
        return $this->titre;
    }

    /**
     * @param string $titre
     */
    public function setTitre(string $titre): void
    {
        $this->titre = $titre;
    }

    /**
     * @return \DateTime
     */
    public function getDate(): ?\DateTime
    {
        return $this->date;
    }

    /**
     * @param \DateTime $date
     */
    public function setDate(\DateTime $date): void
    {
        $this->date = $date;
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

    /**
     * @return string|null
     */
    public function getNomcat(): ?string
    {
        return $this->nomcat;
    }

    /**
     * @param string|null $nomcat
     */
    public function setNomcat(?string $nomcat): void
    {
        $this->nomcat = $nomcat;
    }

    /**
     * @return Categorie
     */
    public function getIdCategorie(): ?Categorie
    {
        return $this->idCategorie;
    }

    /**
     * @param Categorie $idCategorie
     */
    public function setIdCategorie(Categorie $idCategorie): void
    {
        $this->idCategorie = $idCategorie;
    }

    /**
     * @return \Coach
     */
    public function getIdCoachact(): ?\Coach
    {
        return $this->idCoachact;
    }

    /**
     * @param \Coach $idCoachact
     */
    public function setIdCoachact(\Coach $idCoachact): void
    {
        $this->idCoachact = $idCoachact;
    }

    /**
     * Activite constructor.
     */
    /*public function __construct(){
        $this->date = new \DateTime('now');
    }*/


}
