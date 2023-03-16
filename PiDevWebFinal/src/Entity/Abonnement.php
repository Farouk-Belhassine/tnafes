<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use App\Entity\Utilisateur;

/**
 * Abonnement
 *
 * @ORM\Table(name="abonnement", indexes={@ORM\Index(name="fk_id", columns={"id_utilisateur"})})
 * @ORM\Entity
 */
class Abonnement
{
    /**
     * @var integer
     *
     * @ORM\Column(name="id_abonnement", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idAbonnement;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     */
    private $dateFin;

    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=50, nullable=false)
     */
    private $type;

    /**
     *@var \Utilisateur
     *
     * @ORM\ManyToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_utilisateur", referencedColumnName="id")
     * })
     */
    private $idUtilisateur;

    public function getIdAbonnement(): ?int
    {
        return $this->idAbonnement;
    }


    public function getIdUtilisateur(): ?Utilisateur
    {
        return $this->idUtilisateur;
    }

    public function getDateDebut(): ?\DateTime
    {
        return $this->dateDebut;
    }


    public function getDateFin(): ?\DateTime
    {
        return $this->dateFin;
    }


    public function getType(): ?string
    {
        return $this->type;
    }


    public function setIdAbonnement(int $idAbonnement): self
    {
        $this->idAbonnement = $idAbonnement;
    }


    public function setIdUtilisateur(Utilisateur $idUtilisateur): void
    {
        $this->idUtilisateur = $idUtilisateur;
    }


    public function setDateDebut(\DateTime $dateDebut): void
    {
        $this->dateDebut = $dateDebut;
    }


    public function setDateFin(\DateTime $dateFin): void
    {
        $this->dateFin = $dateFin;
    }


    public function setType(string $type): void
    {
        $this->type = $type;
    }


    }
