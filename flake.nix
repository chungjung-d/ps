{
  description = "PS Env";

  inputs.nixpkgs.url = "github:NixOS/nixpkgs";

  outputs = { self, nixpkgs }:
  let
    pkgs = import nixpkgs { system = "aarch64-darwin"; };
    kotlin = pkgs.kotlin;
    jdk = pkgs.jdk23;
  in {
    devShells.aarch64-darwin.default = pkgs.mkShell {
      buildInputs = [
        kotlin
        jdk
      ];

      shellHook = ''
        echo "PS Env"
        echo "Enter KOTLIN $(${kotlin}/bin/bun --version)"
      '';
    };
  };
}

