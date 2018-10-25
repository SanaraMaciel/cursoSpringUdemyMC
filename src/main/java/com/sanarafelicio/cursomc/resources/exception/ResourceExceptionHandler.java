package com.sanarafelicio.cursomc.resources.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.sanarafelicio.cursomc.services.exceptions.AuthorizationException;
import com.sanarafelicio.cursomc.services.exceptions.DataIntegrityException;
import com.sanarafelicio.cursomc.services.exceptions.FileException;
import com.sanarafelicio.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {

	// exceção de objeto not found
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

		// instanciando o standard error
		StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);

	}

	// exceção de excluir categoria com produto (ñ pode excluir categoria se ele tem
	// um produto)
	@ExceptionHandler(DataIntegrityException.class)
	public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException e, HttpServletRequest request) {

		// instanciando o standard error
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}

	// tratamento de exceção
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {

		// instanciando o Validation error
		ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de Validação",
				System.currentTimeMillis());

		// gerando o objeto fieldMessage para cada erro da lista
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			err.addError(x.getField(), x.getDefaultMessage());
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);

	}

	// exceção de usuário para perfil ou não autorização de acesso
	@ExceptionHandler(AuthorizationException.class)
	public ResponseEntity<StandardError> authorization(AuthorizationException e, HttpServletRequest request) {
		// instanciando o standard error
		StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);
	}

	// exceção de arquivo File
	@ExceptionHandler(FileException.class)
	public ResponseEntity<StandardError> file(FileException e, HttpServletRequest request) {
		// instanciando o standard error
		StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
	}
	
	// exceção da AmazonServiceException
		@ExceptionHandler(AmazonServiceException.class)
		public ResponseEntity<StandardError> amazonService(AmazonServiceException e, HttpServletRequest request) {
			//pegando o código para o status da exceção da amazon AWS
			HttpStatus code = HttpStatus.valueOf(e.getErrorCode());
			
			// instanciando o standard error
			StandardError err = new StandardError(code.value(), e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(code).body(err);
		}
		
		// exceção da AmazonClientException
		@ExceptionHandler(AmazonClientException.class)
		public ResponseEntity<StandardError> amazonClient(AmazonClientException e, HttpServletRequest request) {
			// instanciando o standard error
			StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
		}
		
		// exceção da AmazonS3Exception
				@ExceptionHandler(AmazonS3Exception.class)
				public ResponseEntity<StandardError> AmazonS3(AmazonS3Exception e, HttpServletRequest request) {
					// instanciando o standard error
					StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis());
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
				}

}
