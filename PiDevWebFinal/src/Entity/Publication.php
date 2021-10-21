<?php

namespace App\Entity;

use DateTime;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Vich\UploaderBundle\Entity\File;
use Vich\UploaderBundle\Mapping\Annotation as Vich;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Publication
 *
 * @ORM\Table(name="publication", indexes={@ORM\Index(name="fk_iduserpub", columns={"iduser"})})
 * @ORM\Entity
 * @Vich\Uploadable
 */
class Publication
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_pub", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("publications:read")
     */
    private $idPub;
    /**
     * @var int
     * @ORM\Column(name="nbcomment", type="integer", nullable=true)
     * @Groups("publications:read")
     */
    private $nbcomment;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="Le champ contenu est obligatoire")
     * @Groups("publications:read")
     */
    private $contenu;

    /**
     * @var DateTime
     *
     * @ORM\Column(name="date_publication", type="date", nullable=false)
     * @Groups("publications:read")
     */
    private $datePublication;

    /**
     * @var string
     *
     * @ORM\Column(name="urlimage", type="string", length=1000, nullable=false)
     * @Groups("publications:read")
     */
    private $urlimage;
    /**
     * @Assert\NotBlank(message="Importer une image ")
     * @Vich\UploadableField(mapping="images", fileNameProperty="urlimage")
     * @var File
     * @Groups("publications:read")
     */
    private $imageFile;

    /**
     * @var \Utilisateur
     *
     * @ORM\ManyToOne(targetEntity=Utilisateur::class,inversedBy="publication")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="iduser", referencedColumnName="id")
     * })
     */
    private $iduser;
    /**
     * @ORM\OneToMany(targetEntity=Commentaire::class,mappedBy="idpub",cascade={"all"})
     */
    private $commentaire;

    /**
     * Publication constructor.
     */
    public function __construct(Utilisateur $user)
    {
        $this->datePublication = new DateTime('now');
        $this->iduser=$user;
    }

    /**
     * @return int
     */
    public function getIdPub(): ?int
    {
        return $this->idPub;
    }

    /**
     * @param int $idPub
     */
    public function setIdPub(int $idPub): void
    {
        $this->idPub = $idPub;
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

    /**
     * @return DateTime
     */
    public function getDatePublication(): DateTime
    {
        return $this->datePublication;
    }

    /**
     * @param DateTime $datePublication
     */
    public function setDatePublication(DateTime $datePublication): void
    {
        $this->datePublication = $datePublication;
    }

    public function getImageFile()
    {
        return $this->imageFile;
    }

    public function setImageFile($image = null)
    {
        $this->imageFile = $image;

        // VERY IMPORTANT:
        // It is required that at least one field changes if you are using Doctrine,
        // otherwise the event listeners won't be called and the file is lost
        if ($image) {
            // if 'updatedAt' is not defined in your entity, use another property
            $this->updatedAt = new DateTime('now');
        }
    }


    /**
     * @return string
     */
    public function getUrlimage(): ?string
    {
        return $this->urlimage;
    }
    /**
     * @param string $urlimage
     */
    public function setUrlimage($urlimage): void
    {
        $this->urlimage = $urlimage;
    }

    /**
     * @return Utilisateur
     */
    public function getIduser(): Utilisateur
    {
        return $this->iduser;
    }

    /**
     * @param Utilisateur $iduser
     */
    public function setIduser(Utilisateur $iduser): void
    {
        $this->iduser = $iduser;
    }

    /**
     * @return Commentaire
     */
    public function getCommentaire()
    {
        return $this->commentaire;
    }

    /**
     * @param Commentaire $commentaire
     */
    public function setCommentaire($commentaire): void
    {
        $this->commentaire = $commentaire;
    }



    /**
     * @return int
     */
    public function getNbcomment(): ?int
    {
        return $this->nbcomment;
    }

    /**
     * @param int $nbcomment
     */
    public function setNbcomment(int $nbcomment): void
    {
        $this->nbcomment = $nbcomment;
    }




}
