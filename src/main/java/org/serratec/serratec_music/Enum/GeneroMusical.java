package org.serratec.serratec_music.Enum;

import org.serratec.serratec_music.exception.EnumValidationException;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum GeneroMusical {
	
	ROCK, POP,SAMBA,FUNK,SERTANEJO;
	
	@JsonCreator
	public static GeneroMusical verifica(String value) throws EnumValidationException {
		
		for (GeneroMusical genero : values()) {
			if(value.equals(genero.name())) {
				return genero;
			}
		}
		
		throw new EnumValidationException("Gênero inválido. Valores válidos: ROCK, POP, SAMBA, FUNK, SERTANEJO");
	}

}
