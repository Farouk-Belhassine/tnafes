<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Message
 *
 * @ORM\Table(name="message", indexes={@ORM\Index(name="fk_idcoach_message", columns={"id_coach"}), @ORM\Index(name="fk_idclient_message", columns={"id_client"})})
 * @ORM\Entity
 */
class Message
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_message", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMessage;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="heure", type="datetime", nullable=false)
     */
    private $heure;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=50, nullable=false)
     */
    private $contenu;

    /**
     * @var \Client
     *
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Client")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id_client")
     * })
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
    private $idCoach;

    /**
     * @return int
     */
    public function getIdMessage(): int
    {
        return $this->idMessage;
    }

    /**
     * @param int $idMessage
     */
    public function setIdMessage(int $idMessage): void
    {
        $this->idMessage = $idMessage;
    }

    /**
     * @return \DateTime
     */
    public function getHeure(): \DateTime
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
     * @return string
     */
    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    /**
     * @param string $contenu
     */
    public function setContenu(string $contenu): void
    {
        $this->contenu = $contenu;
    }

    public function getIdClient(): ?Client
    {
        return $this->idClient;
    }

    public function setIdClient(Client $idClient): void
    {
        $this->idClient = $idClient;
    }

    public function getIdCoach():   ?Coach
    {
        return $this->idCoach;
    }


    public function setIdCoach(Coach $idCoach): void
    {
        $this->idCoach = $idCoach;
    }
}
