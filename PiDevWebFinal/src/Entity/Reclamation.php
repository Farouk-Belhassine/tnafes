<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints as CaptchaAssert;

/**
 * Reclamation
 *
 * @ORM\Table(name="reclamation", indexes={@ORM\Index(name="fk_idclientreclam", columns={"idclient_rec"})})
 * @ORM\Entity
 */
class Reclamation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_reclamation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("reclamation")
     */
    private $id_reclamation;
    /**
     *  @Assert\NotBlank(message="Captcha code obligatoire")
     * @CaptchaAssert\ValidCaptcha(
     *      message = "
                         La validation CAPTCHA a échoué, réessayez."
     * )
     *
     */
    protected $captchaCode;





    /**
     * @var string
     *
     * @ORM\Column(name="nom_user", type="string", length=20, nullable=true)
     *@Groups("reclamation")
     */
    private $nomUser;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_user", type="string", length=20, nullable=true)
     * @Groups("reclamation")
     */
    private $prenomUser;

    /**
     * @var string
     *
     * @ORM\Column(name="numtel", type="string", length=20, nullable=true)
     * @Groups("reclamation")
     */
    private $numtel;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=75, nullable=true)
     * @Groups("reclamation")
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=75, nullable=false)
     * @Assert\NotBlank(message="Veuillez donner les détails de votre réclamation")
     * @Groups("reclamation")
     */
    private $description;

    /**
     * @var string
     *
     * @ORM\Column(name="objet", type="string", length=20, nullable=false)
     * @Assert\NotBlank(message="Le champ objet est obligatoire")
     * @Groups("reclamation")
     */
    private $objet;

    /**
     * @var string|null
     *
     * @ORM\Column(name="etat", type="string", length=20, nullable=true, options={"default"="En attente"})
     * @Groups("reclamation")
     */
    private $etat = 'En attente';

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date", type="date", nullable=false)
     * @Groups("reclamation")
     */
    private $date;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_traitement", type="date", nullable=true)
     * @Groups("reclamation")
     */
    private $dateTraitement;

    /**
     * @var Utilisateur
     *
     * @ORM\ManyToOne(targetEntity="Utilisateur",inversedBy="reclamation")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idclient_rec", referencedColumnName="id")
     * })
     */
    private $idclientRec;

    /**
     * Reclamation constructor.
     */
    public function __construct(Utilisateur $user)
    {
        $this->date= new \DateTime('now');
        $this->nomUser=$user->getNom();
        $this->prenomUser=$user->getPrenom();
        $this->email=$user->getEmail();
        $this->numtel='+216'.$user->getNumtel();
        $this->idclientRec=$user;
    }

    /**
     * @return int
     */
    public function getIdReclamation(): int
    {
        return $this->id_reclamation;
    }

    /**
     * @param int $idReclamation
     */
    public function setIdReclamation(int $idReclamation): void
    {
        $this->id_reclamation = $idReclamation;
    }

    /**
     * @return string
     */
    public function getNomUser(): ?string
    {
        return $this->nomUser;
    }

    /**
     * @param string $nomUser
     */
    public function setNomUser(string $nomUser): void
    {
        $this->nomUser = $nomUser;
    }

    /**
     * @return string
     */
    public function getPrenomUser(): ?string
    {
        return $this->prenomUser;
    }

    /**
     * @param string $prenomUser
     */
    public function setPrenomUser(string $prenomUser): void
    {
        $this->prenomUser = $prenomUser;
    }

    /**
     * @return string
     */
    public function getNumtel(): ?string
    {
        return $this->numtel;
    }

    /**
     * @param string $numtel
     */
    public function setNumtel(string $numtel): void
    {
        $this->numtel = $numtel;
    }

    /**
     * @return string
     */
    public function getEmail(): ?string
    {
        return $this->email;
    }

    /**
     * @param string $email
     */
    public function setEmail(string $email): void
    {
        $this->email = $email;
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
     * @return string
     */
    public function getObjet(): ?string
    {
        return $this->objet;
    }

    /**
     * @param string $objet
     */
    public function setObjet(string $objet): void
    {
        $this->objet = $objet;
    }

    /**
     * @return string|null
     */
    public function getEtat(): ?string
    {
        return $this->etat;
    }

    /**
     * @param string|null $etat
     */
    public function setEtat(?string $etat): void
    {
        $this->etat = $etat;
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
    public function setDate(?\DateTime $date): void
    {
        $this->date = $date;
    }

    /**
     * @return \DateTime|null
     */
    public function getDateTraitement(): ?\DateTime
    {
        return $this->dateTraitement;
    }

    /**
     * @param \DateTime|null $dateTraitement
     */
    public function setDateTraitement(?\DateTime $dateTraitement): void
    {
        $this->dateTraitement = $dateTraitement;
    }

    /**
     * @return Utilisateur
     */
    public function getIdclientRec(): Utilisateur
    {
        return $this->idclientRec;
    }

    /**
     * @param Utilisateur $idclientRec
     */
    public function setIdclientRec(Utilisateur $idclientRec): void
    {
        $this->idclientRec = $idclientRec;
    }

    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }
}
