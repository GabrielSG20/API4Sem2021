import { Model, Server } from 'miragejs';
import { Injectable } from '@angular/core';

@Injectable()
export default class AppMockedService {
  public mirageJsServer(): Server {
    return new Server({
      models: { result: Model },
      seeds(server) {
        server.db.loadData({
          results: [
            {
              RES_UP: '01 - Terrenos',
              RES_UAR: '0200079',
              RES_COST_CENTER: 5261025101,
              RES_QUANTITATIVE_ACCOUNTING: 1000,
              RES_RESIDUAL_VALUE: 10000.0,
              RES_ACCOUNTING_REMAINING: 500,
              RES_RESIDUAL_REMAINING: 500.0,
              RES_QUANTITATIVE_MATERIAL: 1000,
              RES_MATERIAL_REMAINING: 500,
              RES_CONTRACT_NUMBER: 1,
            },
            {
              RES_UP: '02 - Estruturas de Saneamento',
              RES_UAR: '0800221',
              RES_COST_CENTER: 5261025101,
              RES_QUANTITATIVE_ACCOUNTING: 1000,
              RES_RESIDUAL_VALUE: 10000.0,
              RES_ACCOUNTING_REMAINING: 500,
              RES_RESIDUAL_REMAINING: 500.0,
              RES_QUANTITATIVE_MATERIAL: 1000,
              RES_MATERIAL_REMAINING: 500,
              RES_CONTRACT_NUMBER: 2,
            },
            {
              RES_UP: '03 - Equipamentos de telecontrole e automação',
              RES_UAR: '0903801',
              RES_COST_CENTER: 5261025101,
              RES_QUANTITATIVE_ACCOUNTING: 1000,
              RES_RESIDUAL_VALUE: 10000.0,
              RES_ACCOUNTING_REMAINING: 500,
              RES_RESIDUAL_REMAINING: 500.0,
              RES_QUANTITATIVE_MATERIAL: 1000,
              RES_MATERIAL_REMAINING: 500,
              RES_CONTRACT_NUMBER: 3,
            },
          ],
        });
      },

      routes() {
        this.namespace = 'api';

        this.get('/results', () => {
          return this.schema.all('result');
        });

        this.get('results/close', () => {
          this.shutdown();
          return this.schema.all('result');
        });

        this.post('/results', (schema, request) => {
          const attrs = JSON.parse(request.requestBody);

          return schema.create('result', attrs);
        });
      },
    });
  }
}
