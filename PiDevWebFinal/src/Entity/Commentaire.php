<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use DateTime;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Commentaire
 *
 * @ORM\Table(name="commentaire", indexes={@ORM\Index(name="fk_idpub", columns={"idpub"}), @ORM\Index(name="fk_idusercomment", columns={"idclient_comment"})})
 * @ORM\Entity
 */
class Commentaire
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_comment", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     *  @Groups("comment:read")
     */
    private $idComment;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu_comment", type="string", length=30, nullable=false)
     * @Groups("comment:read")
     */
    private $contenuComment;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_client", type="string", length=20, nullable=true)
     * @Groups("comment:read")
     */
    private $nomClient;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_client", type="string", length=20, nullable=true)
     * @Groups("comment:read")
     */
    private $prenomClient;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="datecomment", type="date", nullable=false)
     * @Groups("comment:read")
     */
    private $datecomment;



    /**
     * @var \Utilisateur
     *
     * @ORM\ManyToOne(targetEntity=Utilisateur::class,inversedBy="commentaire")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idclient_comment", referencedColumnName="id")
     * })
     */
    private $idclientComment;
    /**
     * @var \Publication
     *
     * @ORM\ManyToOne(targetEntity=Publication::class,inversedBy="commentaire")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idpub", referencedColumnName="id_pub")
     * })
     */
    private $idpub;

    /**
     * Commentaire constructor.
     */
    public function __construct($idPub,Utilisateur $user)
    {
        $this->datecomment= new DateTime('now');
        $this->idpub=$idPub;
        $this->nomClient=$user->getNom();
        $this->prenomClient=$user->getPrenom();
        $this->idclientComment=$user;
    }

    /**
     * @return int
     */
    public function getIdComment(): ?int
    {
        return $this->idComment;
    }

    /**
     * @param int $idComment
     */
    public function setIdComment(int $idComment): void
    {
        $this->idComment = $idComment;
    }

    /**
     * @return string
     */
    public function getContenuComment(): ?string
    {
        return $this->contenuComment;
    }

    /**
     * @param string $contenuComment
     */
    public function setContenuComment(string $contenuComment): void
    {
        $this->contenuComment = $contenuComment;
    }

    /**
     * @return string
     */
    public function getNomClient(): ?string
    {
        return $this->nomClient;
    }

    /**
     * @param string $nomClient
     */
    public function setNomClient(string $nomClient): void
    {
        $this->nomClient = $nomClient;
    }

    /**
     * @return string
     */
    public function getPrenomClient(): ?string
    {
        return $this->prenomClient;
    }

    /**
     * @param string $prenomClient
     */
    public function setPrenomClient(string $prenomClient): void
    {
        $this->prenomClient = $prenomClient;
    }

    /**
     * @return \DateTime
     */
    public function getDatecomment(): \DateTime
    {
        return $this->datecomment;
    }

    /**
     * @param \DateTime $datecomment
     */
    public function setDatecomment(\DateTime $datecomment): void
    {
        $this->datecomment = $datecomment;
    }



    /**
     * @return Utilisateur
     */
    public function getIdclientComment(): Utilisateur
    {
        return $this->idclientComment;
    }

    /**
     * @param Utilisateur $idclientComment
     */
    public function setIdclientComment(Utilisateur $idclientComment): void
    {
        $this->idclientComment = $idclientComment;
    }

    /**
     * @return Publication
     */
    public function getIdpub(): Publication
    {
        return $this->idpub;
    }

    /**
     * @param Publication $idpub
     */
    public function setIdpub(Publication $idpub): void
    {
        $this->idpub = $idpub;
    }




}
