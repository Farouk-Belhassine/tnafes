<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Consultation
 *
 * @ORM\Table(name="consultation")
 * @ORM\Entity
 */
class Consultation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_consultation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idConsultation;


    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @Assert\Date()
     * @Assert\GreaterThan("today")
     */
    private $date;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="heure", type="time", nullable=false)
     */
    private $heure;

    /**
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     */
    private $idClient;

    /**
     * @var \Coach
     *
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Coach")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_coach", referencedColumnName="id_coach")
     * })
     */
    private $id;

    /**
     * @return int
     */
    public function getIdConsultation(): int
    {
        return $this->idConsultation;
    }

    /**
     * @param int $idConsultation
     */
    public function setIdConsultation(int $idConsultation): void
    {
        $this->idConsultation = $idConsultation;
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
     * @return \DateTime
     */
    public function getHeure(): ?\DateTime
    {
        return $this->heure;
    }

    /**
     * @param \DateTime $heure
     */
    public function setHeure(\DateTime $heure): void
    {
        $this->heure = $heure;
    }
    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=10, nullable=false)
     */
    private $etat;

    /**
     * @return string
     */
    public function getEtat(): string
    {
        return $this->etat;
    }

    /**
     * @param string $etat
     */
    public function setEtat(string $etat): void
    {
        $this->etat = $etat;
    }


    public function getIdClient(): ?int
    {
        return $this->idClient;
    }


    public function setIdClient(int $idClient): void
    {
        $this->idClient = $idClient;
    }


    public function getId(): ?Coach
    {
        return $this->id;
    }


    public function setId(Coach $idCoach): void
    {
        $this->id = $idCoach;
    }


}
