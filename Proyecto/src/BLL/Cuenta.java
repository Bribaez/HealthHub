package BLL;
import java.time.LocalDate;

public class Cuenta {
	
	private int id;
	private String mail;
	private Usuario usuario;
	private LocalDate fecha;
	

	public Cuenta(int id, String mail, Usuario usuario, LocalDate fecha) {
		super();
		this.id = id;
		this.mail = mail;
		this.usuario = usuario;
		this.fecha = fecha;
	
	}
	
	public Cuenta(String mail, Usuario usuario, LocalDate fecha) {
		super();
		this.id = 0;
		this.mail = mail;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	public Cuenta(String mail,Usuario usuario) {
		super();
		this.id = 0;
		this.mail = mail;
		this.usuario = null;
		this.fecha = fecha;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	@Override
	public String toString() {
		return "Cuenta [id=" + id + ", mail=" + mail + ", usuario=" + usuario + ", fecha=" + fecha + "]";
	}
	
	
	
}
