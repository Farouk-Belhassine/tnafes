<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints as CaptchaAssert;



/**
 * Participation
 *
 * @ORM\Table(name="participation", indexes={@ORM\Index(name="fk_event", columns={"id_evenement"}), @ORM\Index(name="fk_participation", columns={"id_client"})})
 * @ORM\Entity
 */
class Participation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_participation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idParticipation;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255)
     * @Assert\NotBlank(message="nom obligatoire")

     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255)
     * @Assert\NotBlank(message="prenom obligatoire")

     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="e_mail", type="string", length=255)
     * @Assert\NotBlank(message="e_mail obligatoire")

     */
    private $eMail;

    /**
     * @var int|null
     *
     * @ORM\Column(name="nb_participant", type="integer", nullable=true)
     */
    private $nbParticipant;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="Date_part", type="date", nullable=false)
     */
    private $datePart;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_evenement", type="date", nullable=false)
     */
    private $dateEvenement;

    /**
     * @var \Event
     *
     * @ORM\ManyToOne(targetEntity="Event",inversedBy="participation")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_evenement", referencedColumnName="id_event")
     * })
     */
    private $idEvenement;

    /**
     * @var Utilisateur
     *
     * @ORM\ManyToOne(targetEntity="Utilisateur",inversedBy="participation")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_client", referencedColumnName="id")
     * })
     */
    private $idClient;

    /**
     * Participation constructor.
     * @param Event $idEvenement
     * @param Utilisateur $idClient
     */
    public function __construct(Event $idEvenement, Utilisateur $user)
    {
        $this->idEvenement = $idEvenement;
        $this->idClient=$user;
        $this->nom=$user->getNom();
        $this->prenom=$user->getPrenom();
        $this->eMail=$user->getEmail();

    }

    /**
     * @return int
     */
    public function getIdParticipation(): int
    {
        return $this->idParticipation;
    }

    /**
     * @param int $idParticipation
     */
    public function setIdParticipation(int $idParticipation): void
    {
        $this->idParticipation = $idParticipation;
    }

    /**
     * @return string|null
     */
    public function getNom(): ?string
    {
        return $this->nom;
    }

    /**
     * @param string|null $nom
     */
    public function setNom(?string $nom): void
    {
        $this->nom = $nom;
    }

    /**
     * @return string|null
     */
    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    /**
     * @param string|null $prenom
     */
    public function setPrenom(?string $prenom): void
    {
        $this->prenom = $prenom;
    }

    /**
     * @return string|null
     */
    public function getEMail(): ?string
    {
        return $this->eMail;
    }

    /**
     * @param string|null $eMail
     */
    public function setEMail(?string $eMail): void
    {
        $this->eMail = $eMail;
    }

    /**
     * @return int|null
     */
    public function getNbParticipant(): ?int
    {
        return $this->nbParticipant;
    }

    /**
     * @param int|null $nbParticipant
     */
    public function setNbParticipant(?int $nbParticipant): void
    {
        $this->nbParticipant = $nbParticipant;
    }

    /**
     * @return \DateTime|null
     */
    public function getDatePart(): ?\DateTime
    {
        return $this->datePart;
    }

    /**
     * @param \DateTime $datePart
     */
    public function setDatePart(?\DateTime $datePart): void
    {
        $this->datePart = $datePart;
    }

    /**
     * @return \DateTime|null
     */
    public function getDateEvenement(): ?\DateTime
    {
        return $this->dateEvenement;
    }

    /**
     * @param \DateTime $dateEvenement
     */
    public function setDateEvenement(?\DateTime $dateEvenement): void
    {
        $this->dateEvenement = $dateEvenement;
    }

    /**
     * @return Event
     */
    public function getIdEvenement(): ?Event
    {
        return $this->idEvenement;
    }

    /**
     * @param \Event $idEvenement
     */
    public function setIdEvenement(\Event $idEvenement): void
    {
        $this->idEvenement = $idEvenement;
    }

    /**
     * @return Utilisateur
     */
    public function getIdClient(): Utilisateur
    {
        return $this->idClient;
    }

    /**
     * @param Utilisateur $idClient
     */
    public function setIdClient(Utilisateur $idClient): void
    {
        $this->idClient = $idClient;
    }
    /**
     * @CaptchaAssert\ValidCaptcha(
     *      message = "CAPTCHA validation failed, try again."
     * )
     */
    protected $captchaCode;

    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }


}
