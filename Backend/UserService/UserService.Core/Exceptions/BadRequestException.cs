﻿namespace UserService.Core.Exceptions;

public abstract class BadRequestException(string message) : Exception(message);