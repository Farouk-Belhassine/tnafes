<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Component\Security\Core\User\UserInterface;

/**
 * Utilisateur
 *
 * @ORM\Entity(repositoryClass=UtilisateurRepository::class)
 * @ORM\Table(name="utilisateur")
 * @ORM\Entity
 */
class Utilisateur implements UserInterface, \Serializable
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=20, nullable=false)
     * @Assert\NotBlank (message="Nom obligatoire!")
     */
    private $nom;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=20, nullable=false)
     * @Assert\NotBlank (message="Prenom obligatoire!")
     */
    private $prenom;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=75, nullable=false)
     * @Assert\NotBlank (message="mail obligatoire!")
     * @Assert\Email (message="Mail non valide!")
     */
    private $email;

    /**
     * @var int
     *
     * @ORM\Column(name="numtel", type="integer", nullable=false)
     * @Assert\NotBlank (message="Numero obligatoire!")
     * @Assert\Length(min = 8, max = 20, minMessage = "Le numéro doit contenir au moins 8 chiffres", maxMessage = "max_lenght")
     */
    private $numtel;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=128, nullable=false)
     * @Assert\NotBlank (message="Password obligatoire!")
     */
    private $password;


    /**
     * @ORM\OneToMany(targetEntity=Publication::class,mappedBy="iduser",cascade={"all"})
     */
    private $publication;

    /**
     * @ORM\OneToMany(targetEntity=Reclamation::class,mappedBy="idclientRec",cascade={"all"})
     */
    private $reclamation;
    /**
     * @ORM\OneToMany(targetEntity=Commentaire::class,mappedBy="idclientComment",cascade={"all"})
     */
    private $commentaire;
    /**
     * @ORM\OneToMany(targetEntity=Participation::class,mappedBy="idClient",cascade={"all"})
     */
   private  $participation;

    /**
     * @var string
     *
     * @ORM\Column(name="roles", type="json", nullable=false)
     */
    private $roles;

    public function getId(){return $this->id;}
    function getNom(){return $this->nom;}
    function getPrenom(){return $this->prenom;}
    function getEmail(){return $this->email;}
    function getNumtel(){return $this->numtel;}
    function getPassword(){return $this->password;}
    public function getRoles(){return $this->roles;}

    public function setId(int $id){$this->id = $id;}
    function setNom($nom){$this->nom=$nom;}
    function setPrenom($prenom){$this->prenom=$prenom;}
    function setEmail($email){$this->email=$email;}
    function setNumtel($numtel){$this->numtel=$numtel;}
    function setPassword($password){$this->password=$password;}
    function setRoles($roles){return $this->roles=$roles;}

    public function __toString(){
        $var = strval($this->id);
        return $var;
    }

    public function getSalt()
    {
        // you *may* need a real salt depending on your encoder
        // see section on salt below
        return null;
    }

    public function eraseCredentials()
    {
        //empty
    }

    public function serialize()
    {
        return serialize(array(
            $this->id,
            $this->email,
            $this->password,
            // see section on salt below
            // $this->salt,
        ));
    }

    public function unserialize($serialized)
    {
        list (
            $this->id,
            $this->email,
            $this->password,
            // see section on salt below
            // $this->salt
        ) = unserialize($serialized, array('allowed_classes' => false));
    }

    public function getUsername()
    {
        return $this->email;
    }


    /**
     * @return Publication
     */
    public function getPublication()
    {
        return $this->publication;
    }

    /**
     * @param Publication $publication
     */
    public function setPublication($publication): void
    {
        $this->publication = $publication;
    }

    /**
     * @return Reclamation
     */
    public function getReclamation()
    {
        return $this->reclamation;
    }

    /**
     * @param Reclamation $reclamation
     */
    public function setReclamation($reclamation): void
    {
        $this->reclamation = $reclamation;
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
     * @return Participation
     */
    public function getParticipation()
    {
        return $this->participation;
    }

    /**
     * @param Participation $participation
     */
    public function setParticipation($participation): void
    {
        $this->participation = $participation;
    }

}
