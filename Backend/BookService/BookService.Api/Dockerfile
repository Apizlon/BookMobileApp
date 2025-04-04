FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
ARG BUILD_CONFIGURATION=Release
WORKDIR /src
COPY ["BookService/BookService.Api/BookService.Api.csproj", "BookService/BookService.Api/"]
RUN dotnet restore "BookService/BookService.Api/BookService.Api.csproj"
COPY . .
WORKDIR "/src/BookService/BookService.Api"
RUN dotnet build "BookService.Api.csproj" -c $BUILD_CONFIGURATION -o /app/build

FROM build AS publish
ARG BUILD_CONFIGURATION=Release
RUN dotnet publish "BookService.Api.csproj" -c $BUILD_CONFIGURATION -o /app/publish /p:UseAppHost=false

FROM base AS final
WORKDIR /app
COPY --from=publish /app/publish .
ENTRYPOINT ["dotnet", "BookService.Api.dll"]
