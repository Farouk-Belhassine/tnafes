<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use phpDocumentor\Reflection\File;
use PhpParser\Node\Scalar\String_;
use Symfony\Component\Validator\Constraints as Assert;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints\DateTime;
use Symfony\Component\Serializer\Annotation\Groups;



/**
 * Event
 *
 * @ORM\Table(name="event", indexes={@ORM\Index(name="fk_idcatE", columns={"category"})})
 * @ORM\Entity
 * @Vich\Uploadable()
 */
class Event
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_event", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups ("post:read")
     *
     */
    private $idEvent;

    /**
     * @return File
     */
    public function getImageFile()
    {
        return $this->imageFile;
    }

    /**
     * @param File $imageFile
     */
    public function setImageFile($imageFile=null): void
    {
        $this->imageFile = $imageFile;

        if ($imageFile) {
            $this->updatedAt = new DateTime('now');
        }
    }




   /**
     * @var File
     * @Vich\UploadableField(mapping="images",fileNameProperty="description")
     *@Assert\NotBlank(message="Image obligatoire")
     * @Groups ("post:read")
     */

    private $imageFile;
    /**
     * @var String
     * @ORM\Column(type="string")
     * @Groups ("post:read")
     */
    private $description;


    /**
     * @var int|null
     * @ORM\Column(name="id_coach", type="integer", nullable=true)
     * @Groups ("post:read")
     */
    private $idCoach;

    /**
     * @var string|null
     *
     * @ORM\Column(name="lieu", type="string", length=255, nullable=true)
     * @Groups ("post:read")
     */
    private $lieu;

    /**
     * @var int|null
     *
     * @ORM\Column(name="nb_place", type="integer", nullable=true)
     * @Assert\NotBlank(message="nombre de place obligatoire")
     * @Groups ("post:read")
     */
    private $nbPlace;

    /**
     * @var \DateTime|null
     *
     * @ORM\Column(name="date_event", type="date", nullable=true)
     * @Assert\NotBlank(message="Date obligatoire")
     * @Groups ("post:read")
     * @Assert\Range(
     *      min = "now",
     *     max= "+30days",
     *      notInRangeMessage = "La date doit etre supperieur a notre date actuelle {{ min }} et ne doit pas depasser {{ max }} ",
     * )
     */
    private $dateEvent;

    /**
     * @var string|null
     *
     * @ORM\Column(name="descriptions", type="string", length=255, nullable=true)
     * @Groups ("post:read")
     */
    private $descriptions;



    /**
     * @var \CategorieEvent
     *
     * @ORM\ManyToOne(targetEntity=CategorieEvent::class, inversedBy="evenement")
     * @ORM\JoinColumns({
     * @ORM\JoinColumn(name="category", referencedColumnName="id_categ_event")
     * })
     *@Groups ("post:read")
     */
    private $category;

    /**
     * @return int
     */
    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }

    /**
     * @param int $idEvent
     */
    public function setIdEvent(int $idEvent): void
    {
        $this->idEvent = $idEvent;
    }

    /**
     * @return int|null
     */
    public function getIdCoach(): ?int
    {
        return $this->idCoach;
    }

    /**
     * @param int|null $idCoach
     */
    public function setIdCoach(?int $idCoach): void
    {
        $this->idCoach = $idCoach;
    }

    /**
     * @return string|null
     */
    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    /**
     * @param string|null $lieu
     */
    public function setLieu(?string $lieu): void
    {
        $this->lieu = $lieu;
    }

    /**
     * @return int|null
     */
    public function getNbPlace(): ?int
    {
        return $this->nbPlace;
    }

    /**
     * @param int|null $nbPlace
     */
    public function setNbPlace(?int $nbPlace): void
    {
        $this->nbPlace = $nbPlace;
    }

    /**
     * @return \DateTime|null
     */
    public function getDateEvent(): ?\DateTime
    {
        return $this->dateEvent;

    }

    /**
     * @param \DateTime|null $dateEvent
     */
    public function setDateEvent(?\DateTime $dateEvent): void
    {
        $this->dateEvent = $dateEvent;
    }

    /**
     * @return string|null
     */
    public function getdescriptions(): ?string
    {
        return $this->descriptions;
    }

    /**
     * @param string|null $descriptions
     */
    public function setdescriptions(?string $descriptions): void
    {
        $this->descriptions = $descriptions;
    }

    /**
     * @return string|null
     */
    public function getDescription(): ?string
    {
        return $this->description;
    }

    /**
     * @param string|null $description
     */
    public function setDescription(?string $description): void
    {
        $this->description = $description;
    }

    /**
     * @return CategorieEvent
     */
    public function getcategory(): ?CategorieEvent
    {
        return $this->category;
    }

    /**
     * @param CategorieEvent $category
     */
    public function setcategory(CategorieEvent $category): void
    {
        $this->category = $category;
    }
    public function __toString() {
        return $this->description;
    }


}
