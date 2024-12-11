package br.com.VNLocacoes.VNLocacoes.controller;

import br.com.VNLocacoes.VNLocacoes.dto.CarroDTO;
import br.com.VNLocacoes.VNLocacoes.repository.CategoriaRepository;
import br.com.VNLocacoes.VNLocacoes.repository.MarcaRepository;
import br.com.VNLocacoes.VNLocacoes.service.CarroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/servicos/carro")
@CrossOrigin(origins = "*")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @PostMapping
    public ResponseEntity<CarroDTO> salvarCarro(@RequestBody @Valid CarroDTO carro) {
        carro.setCategoria(categoriaRepository.findById(carro.getCategoria().getId()).get());
        carro.setMarca(marcaRepository.findById(carro.getMarca().getId()).get());
        CarroDTO carroSalvo = carroService.salvarCarro(carro);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(carroSalvo);
    }

    @GetMapping
    public ResponseEntity<List<CarroDTO>> listarTodosOsCarros() {
        List<CarroDTO> listaCarros = carroService.listarTodosOsCarros();

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaCarros);

    }

    @GetMapping("/busca")
    public ResponseEntity<List<CarroDTO>> listarCarrosPorDisponibilidade(@RequestParam(name = "disponibilidade") boolean disponibilidade) {
        List<CarroDTO> listaCarros = carroService.listarCarrosPorDisponibilidade(disponibilidade);

        return ResponseEntity.status(HttpStatus.OK)
                .body(listaCarros);

    }

    @GetMapping("/{placa}")
    public ResponseEntity<CarroDTO> buscarCarroPorPlaca(@PathVariable(name = "placa") String placa) {
        CarroDTO carroExistente = carroService.buscarCarroPorPlaca(placa);

        return ResponseEntity.status(HttpStatus.OK)
                .body(carroExistente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarroDTO> atualizarDadosDoCarro(@PathVariable(name = "id") Long id, @RequestBody @Valid CarroDTO carro) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(carroService.atualizarDadosDoCarro(id, carro));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarCarro(@PathVariable Long id) {
        boolean statusDelecao = carroService.deletarCarro(id);

        if (statusDelecao) {
            // TESTE DE RESPONSE DA API
            HashMap<String, Object> response = new HashMap<>();
            response.put("status", 200);
            response.put("mensagem", "Carro deletado com sucesso");

            return ResponseEntity.status(HttpStatus.OK)
                    .body(response);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Houve algum erro na requisição, tente novamente mais tarde");
    }
}
