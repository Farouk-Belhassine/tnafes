<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints as CaptchaAssert;

/**
 * Reclamation2
 *
 * @ORM\Table(name="reclamation", indexes={@ORM\Index(name="fk_idclientreclam", columns={"idclient_rec"})})
 * @ORM\Entity
 */
class Reclamation2
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



    public function getCaptchaCode()
    {
        return $this->captchaCode;
    }

    public function setCaptchaCode($captchaCode)
    {
        $this->captchaCode = $captchaCode;
    }
}
