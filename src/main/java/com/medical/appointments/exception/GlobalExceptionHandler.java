// package com.medical.appointments.exception;

// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.validation.ConstraintViolation;
// import jakarta.validation.ConstraintViolationException;
// import org.springframework.dao.DataIntegrityViolationException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.validation.FieldError;
// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ControllerAdvice;
// import org.springframework.web.bind.annotation.ResponseBody;
// import org.springframework.web.context.request.WebRequest;

// /**
//  * Global exception handler to provide consistent error responses across the API.
//  * 
//  * This class handles various exceptions that might occur during API requests
//  * and converts them into standardized ApiError responses with appropriate HTTP status codes.
//  * 
//  * @author Medical Appointments Team
//  * @version 1.0.0
//  */
// @ControllerAdvice
// public class GlobalExceptionHandler {
    
//     /**
//      * Handles ResourceNotFoundException and returns a 404 Not Found response.
//      * 
//      * @param ex The exception that was thrown
//      * @param request The current request
//      * @return A ResponseEntity containing an ApiError
//      */
//     @ExceptionHandler(ResourceNotFoundException.class)
//     @ResponseBody
//     public ResponseEntity<ApiError> handleResourceNotFoundException(
//             ResourceNotFoundException ex, HttpServletRequest request) {
        
//         ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
//         apiError.setPath(request.getRequestURI());
//         return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
//     }
    
//     /**
//      * Handles ResourceAlreadyExistsException and returns a 409 Conflict response.
//      * 
//      * @param ex The exception that was thrown
//      * @param request The current request
//      * @return A ResponseEntity containing an ApiError
//      */
//     @ExceptionHandler(ResourceAlreadyExistsException.class)
//     @ResponseBody
//     public ResponseEntity<ApiError> handleResourceAlreadyExistsException(
//             ResourceAlreadyExistsException ex, HttpServletRequest request) {
        
//         ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getMessage());
//         apiError.setPath(request.getRequestURI());
//         return new ResponseEntity<>(apiError, HttpStatus.CONFLICT);
//     }
    
//     /**
//      * Handles validation exceptions and returns a 400 Bad Request response.
//      * 
//      * @param ex The exception that was thrown
//      * @param request The current request
//      * @return A ResponseEntity containing an ApiError with validation errors
//      */
//     @ExceptionHandler(MethodArgumentNotValidException.class)
//     @ResponseBody
//     public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
//             MethodArgumentNotValidException ex, HttpServletRequest request) {
        
//         ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error");
//         apiError.setPath(request.getRequestURI());
        
//         ex.getBindingResult().getAllErrors().forEach(error -> {
//             String fieldName = ((FieldError) error).getField();
//             String errorMessage = error.getDefaultMessage();
//             apiError.addError(fieldName + ": " + errorMessage);
//         });
        
//         return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//     }
    
//     /**
//      * Handles constraint violation exceptions and returns a 400 Bad Request response.
//      * 
//      * @param ex The exception that was thrown
//      * @param request The current request
//      * @return A ResponseEntity containing an ApiError with validation errors
//      */
//     @ExceptionHandler(ConstraintViolationException.class)
//     @ResponseBody
//     public ResponseEntity<ApiError> handleConstraintViolationException(
//             ConstraintViolationException ex, HttpServletRequest request) {
        
//         ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, "Validation error");
//         apiError.setPath(request.getRequestURI());
        
//         for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
//             apiError.addError(violation.getPropertyPath() + ": " + violation.getMessage());
//         }
        
//         return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//     }
    
//     /**
//      * Handles data integrity violation exceptions and returns a 400 Bad Request response.
//      * 
//      * @param ex The exception that was thrown
//      * @param request The current request
//      * @return A ResponseEntity containing an ApiError
//      */
//     @ExceptionHandler(DataIntegrityViolationException.class)
//     @ResponseBody
//     public ResponseEntity<ApiError> handleDataIntegrityViolationException(
//             DataIntegrityViolationException ex, HttpServletRequest request) {
        
//         ApiError apiError = new ApiError(
//                 HttpStatus.BAD_REQUEST,
//                 "Database constraint violation: " + ex.getMostSpecificCause().getMessage()
//         );
//         apiError.setPath(request.getRequestURI());
//         return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
//     }
    
//     /**
//      * Handles all other exceptions and returns a 500 Internal Server Error response.
//      * 
//      * @param ex The exception that was thrown
//      * @param request The current request
//      * @return A ResponseEntity containing an ApiError
//      */
//     @ExceptionHandler(Exception.class)
//     @ResponseBody
//     public ResponseEntity<ApiError> handleGlobalException(
//             Exception ex, HttpServletRequest request) {
        
//         ApiError apiError = new ApiError(
//                 HttpStatus.INTERNAL_SERVER_ERROR,
//                 "An unexpected error occurred: " + ex.getMessage()
//         );
//         apiError.setPath(request.getRequestURI());
//         return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
//     }
// }