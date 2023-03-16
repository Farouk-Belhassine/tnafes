<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Client
 *
 * @ORM\Table(name="client")
 * @ORM\Entity
 */
class Client
{
    /**
     * @var bool|null
     *
     * @ORM\Column(name="permaban", type="boolean", nullable=true)
     */
    private $permaban = '0';

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="dateblock", type="date", nullable=true)
     */
    private $dateblock;

    /**
     * @var \Utilisateur
     *
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="NONE")
     * @ORM\OneToOne(targetEntity="Utilisateur")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id")
     * })
     */
    private $idClient;

    public function getPermaban(){return $this->permaban;}

    public function setPermaban($permaban){$this->permaban = $permaban;}

    public function getDateblock(): ?\DateTimeInterface{return $this->dateblock;}

    public function setDateblock(?\DateTimeInterface $dateblock){$this->dateblock = $dateblock;}

    public function getIdClient(){return $this->idClient;}

    public function setIdClient(?Utilisateur $idClient){$this->idClient = $idClient;}




}
