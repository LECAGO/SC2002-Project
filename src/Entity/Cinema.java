package Entity;

import ExceptionPackage.CinemaCodeNameException;

import java.io.Serializable;

/**
 * This Cinema class contains data about itself. Different (non-normal) types of Cinema are extensions of this class.
 * @author Tan Chuan Liang
 * @version 1.0
 * @since 2022-10-31
 */
public class Cinema implements Serializable {
	/**
	 * Enum representing type of Cinema (Normal, 3D, Premium)
	 */
	public enum CinemaTypes {
		CIN_NORMAL,
		CIN_3D,
		CIN_PREMIUM,
	}
	private String name;
	private String nameCode;
	private CinemaTypes cinemaType;

	/**
	 * Constructor for a Cinema.
	 * @param name Name of the cinema.
	 * @param nameCode Codename of the cinema.
	 * @param cinemaType Enum type of cinema.
	 * @throws CinemaCodeNameException nameCode is checked to be 5 characters long.
	 */
	public Cinema(String name, String nameCode, CinemaTypes cinemaType) throws CinemaCodeNameException{
		this.name = name;
		if(nameCode.length()<10){
			throw new CinemaCodeNameException();
		} else {
			this.nameCode = nameCode;
		}
		this.cinemaType = cinemaType;

	}

	/**
	 * Overloaded constructor similar to {@link Cinema#Cinema(String, String, CinemaTypes)}.
	 * Omitting CinemaTypes parameter, the default value of cinemaType is set to CIN_NORMAL.
	 * @see Cinema#Cinema(String, String, CinemaTypes)
	 */
	public Cinema(String name, String nameCode) throws CinemaCodeNameException{
		this.name = name;
		if(nameCode.length()<10){
			throw new CinemaCodeNameException();
		} else {
			this.nameCode = nameCode;
		}
		this.cinemaType = CinemaTypes.CIN_NORMAL;

	}

	/**
	 * Get the name of the cinema.
	 * @return Name of cinema.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the cinema.
	 * @param name Name of cinema.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the codename of the cinema.
	 * @return Codename of the cinema.
	 */
	public String getNameCode() {
		return nameCode;
	}

	/**
	 * Set the codename of the cinema.
	 * @param nameCode Codename of the cinema.
	 * @throws CinemaCodeNameException nameCode is checked to be 5 characters long.
	 */
	public void setNameCode(String nameCode) throws CinemaCodeNameException{
		if(nameCode.length()<10){
			throw new CinemaCodeNameException();
		} else {
			this.nameCode = nameCode;
		}
	}

	/**
	 * Get the type of cinema.
	 * @return CinemaType of cinema.
	 */
	public CinemaTypes getCinemaType() {
		return cinemaType;
	}

	/**
	 * Set the type of cinema.
	 * @param cinemaType CinemaType of cinema.
	 */
	public void setCinemaType(CinemaTypes cinemaType) {
		this.cinemaType = cinemaType;
	}

}
